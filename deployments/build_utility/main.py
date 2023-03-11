import json
import os
import shutil
import subprocess
import sys

gradle_command = "gradle --build-file"
k8s_delete = "kubectl delete"
k8s_create = "kubectl create"

dockerfile_manifest = "Dockerfile"
docker_build_command = "docker build"
docker_rmi_command = "docker rmi"
docker_push_command = "docker push"
check_gradle_command = "gradle --version"
gradle_dir_ident = "build.gradle"
dockerfile_ident = "Dockerfile"
dockerfile_get_images = "docker images"

k8s_deployments = "k8s_deployments"
docker_registry = "docker_registry"
remove_docker_images_before_build = "docker_remove_previous_images_before_build"
gradle_build_command = "gradle_build_command"
build_associations = "build_associations"
docker_build_tag = "docker_build_tag"
path_prefix = os.path.realpath(__file__)[:-7]

properties = dict()


def read_config(config_path):
    global properties
    with open(config_path, mode='r') as file:
        return json.load(file)


def check_gradle_in_os():
    gradle = "Gradle"
    output = subprocess.getoutput(check_gradle_command)
    return gradle in output


def build_jar(path_to_build):
    global properties
    errors = "[ERROR]"
    build_cmd = gradle_command + " " + path_to_build + " "
    build_cmd += properties[gradle_build_command]
    print("cmd:", build_cmd)
    out = subprocess.getoutput(build_cmd)
    if errors in out:
        print("Build of", path_to_build, " failed")
        print(out)
        return False
    else:
        print(path_to_build, "Build successfully")
        return True


def copy_jar(path_to_build, dest_path):
    path = os.path.join(path_to_build, "build", "libs")
    jar = [f for f in os.listdir(path) if f.endswith(".jar")]
    if len(jar) == 0:
        print("Jar file not found, please check build correct")
        return
    jar = os.path.join(path_to_build, "build", "libs", jar[0])
    shutil.copy(jar, os.path.join(dest_path, "jar"))


def scan_for_gradle_projects(in_path):
    gradle_projects = list()
    for path, _, files in os.walk(in_path):
        for file in files:
            if file == gradle_dir_ident:
                gradle_projects.append(os.path.abspath(path))
                print("Detected gradle project in:", os.path.abspath(path))
                break
    return gradle_projects


def scan_for_dockerfiles(in_path):
    docker_fls = list()
    for path, _, files in os.walk(in_path):
        for file in files:
            if file == dockerfile_ident:
                docker_fls.append(os.path.abspath(path))
                print("Detected Dockerfile in:", os.path.abspath(path))
    if len(docker_fls) == 0:
        print("No Dockerfile detected in:", in_path)
    return docker_fls


def build_docker_image(path_to_dockerfile, build_name):
    failed = "failed"
    os.chdir(path_to_dockerfile)
    build_cmd = docker_build_command
    build_cmd += " -t "
    build_cmd += build_docker_image_name(build_name) + " ."
    print("cmd:", build_cmd)
    out = subprocess.getoutput(build_cmd)
    if failed in out:
        print("Build of docker images of:", path_to_dockerfile, "failed")
        print(out)
    else:
        print("Docker image:", path_to_dockerfile, "builded successfully")


def build_docker_image_name(name, use_tag=True):
    full_name = properties[docker_registry] + "/" + name
    if use_tag and (properties[docker_build_tag] is not None or len(properties[docker_build_tag]) != 0):
        full_name += ":" + properties[docker_build_tag]
    return full_name


def push_docker_image(name):
    name = build_docker_image_name(name)
    command = docker_push_command + " " + name
    print("cmd:", command)
    subprocess.getoutput(command)
    print("Docker image:", name, "pushed successfully")


def remove_previous_docker_image(name):
    if not properties[remove_docker_images_before_build]:
        return
    name = build_docker_image_name(name, False)
    out = subprocess.getoutput(dockerfile_get_images).split("\n")
    for line in out:
        if name in line:
            line = line.split(" ")
            while "" in line:
                line.remove("")
            image_id = line[2]
            command = docker_rmi_command + " -f " + image_id
            res = subprocess.getoutput(command)
            print(res)


def create_k8s_deployment(yaml):
    delete_command = k8s_delete + " -f " + yaml
    create_command = k8s_create + " -f " + yaml
    subprocess.getoutput(delete_command)
    create = subprocess.getoutput(create_command)
    print(create)


def wrap_path(sub_path):
    path = path_prefix
    if path is None or len(path) == 0:
        return sub_path
    return (path if path.endswith(os.sep) else path + os.sep) + sub_path


if __name__ == "__main__":
    try:
        if len(sys.argv) != 2:
            print("Build config not specified!")
            sys.exit(1)
        properties = read_config("build_config.json")
        projects = set()
        for name, f in properties[build_associations].items():
            f = wrap_path(f)
            projects.update(scan_for_gradle_projects(f))
        docker_files = dict()
        for name, f in properties[build_associations].items():
            f = wrap_path(f)
            if f not in projects:
                continue
            df = scan_for_dockerfiles(os.path.join(f, "deployments"))
            if len(df) != 1:
                for fl in df:
                    if fl.endswith(name):
                        docker_files[name] = fl
            else:
                docker_files[name] = df[0]
        if not check_gradle_in_os():
            print("Gradle not installed, please install gradle and try again")
            sys.exit(1)
        for name, f in properties[build_associations].items():
            file = os.path.join(path_prefix, f, "build.gradle")
            if build_jar(file):
                copy_jar(os.path.join(path_prefix, f), docker_files[name])

        for k, v in docker_files.items():
            remove_previous_docker_image(k)
            build_docker_image(v, k)
            push_docker_image(k)

        for depl in properties[k8s_deployments]:
            create_k8s_deployment(wrap_path(depl))

    except KeyboardInterrupt:
        print("Stopping...")
        sys.exit(1)
    except Exception:
        print("An error occured")
        sys.exit(1)
    print("Build done")
