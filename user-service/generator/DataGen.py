import json
import requests
import time
from tqdm import tqdm

# Load JSON data from file
with open('request.json', 'r') as file:
    json_data = json.load(file)

with open('config.json', 'r') as file:
    config = json.load(file)

# Endpoint URL of your Java application
endpoint_url = config["url"]


# Function to handle the POST request
def post():
    res = requests.post(endpoint_url, json=json_data)
    if res.status_code == 500:
        time.sleep(1)
        return post()
    return res


# Number of iterations
num_iterations = config["interation"]

# Start time
start_time = time.time()

# Send POST request with JSON data
with tqdm(total=num_iterations) as pbar:
    for i in range(num_iterations + 1):
        json_data["name"] = "user: " + str(i)
        response = post()
        pbar.set_description(f"count: {i}, status: {response.status_code}")
        pbar.update(1)

# End time
end_time = time.time()

# Elapsed time
elapsed_time = end_time - start_time

# Print the final status code and elapsed time
print("\nFinal Status Code:", response.status_code)
print("Elapsed Time: {:.2f} seconds".format(elapsed_time))
