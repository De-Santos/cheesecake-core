import json
import os
import shutil
import subprocess

with open("C:/CodeFile/JavaProjects/cheesecake-core/product-service/docker/AutoBuilder/config.json") as f:
    config = json.load(f)


def remove(path):
    if os.path.exists(path):
        os.remove(path)
        print("File deleted.")


os.chdir(config["project_path"])
remove(config["jar_file"])
subprocess.run("gradle clean", shell=True, check=True)
subprocess.run(config["gradle_build_command"], shell=True, check=True)
remove(config["target_dir"] + "/" + config["jar_name"])
shutil.move(config["jar_file"], config["target_dir"])
os.chdir(config["docker_dir"])
subprocess.run(config["build_command"], shell=True, check=True)
subprocess.run(config["push_command"], shell=True, check=True)
subprocess.run(config["kubernetes_update_command"], shell=True, check=True)
podName = subprocess.run("kubectl get pods", shell=True, check=True, stdout=subprocess.PIPE, text=True).stdout.strip()
podName = podName[1:-1]
print(podName)
