import json
import os
import shutil
import subprocess

# read configuration from JSON file
with open("C:/CodeFile/JavaProjects/cheesecake-core/product-service/docker/AutoBuilder/config.json") as f:
    config = json.load(f)

# change directory to the project directory
os.chdir(config["project_path"])

# check if the file exists before deleting
if os.path.exists(config["jar_file"]):
    os.remove(config["jar_file"])
    print("File deleted.")

# build JAR file using Gradle
subprocess.run(config["gradle_build_command"], shell=True, check=True)

targetJarDir = config["target_dir"] + "/" + config["jar_name"]
if os.path.exists(targetJarDir):
    os.remove(targetJarDir)
    print("File deleted.")

# move JAR file to the target directory
shutil.move(config["jar_file"], config["target_dir"])
# os.rename(os.path.join(config["project_path"], config["jar_file"]), config["target_dir"])

os.chdir(config["docker_dir"])
# build Docker image
subprocess.run(config["build_command"], shell=True, check=True)

# push Docker image to the local registry

subprocess.run(config["push_command"], shell=True, check=True)

# update Kubernetes pod
subprocess.run(config["kubernetes_update_command"], shell=True, check=True)
podName = subprocess.run("kubectl get pods --selector=app=product-service -o jsonpath='{.items[*].metadata.name}'",
                         shell=True, check=True, stdout=subprocess.PIPE, text=True).stdout.strip()
podName = podName[1:-1]  # Remove single quotes from podName
print(f"kubectl logs -f {podName}")