"""
references
https://www.geeksforgeeks.org/python-requests-post-request-with-headers-and-body/?ref=rp
"""

jwt = ""
login = False
username = ""
import requests
baseurl = "http://localhost:8080"


def main():
	global jwt
	while True:
		com = input(">>> ")
		if com not in ["LOGIN", "REG", "COMM", "BEAC"]:
			print("Please enter a valid command: \"LOGIN\", \"REG\", \"COMM\", \"BEAC\"")
		if com == "LOGIN":
			login()
		elif com == "REG":
			register()
		elif com == "COMM":
			command()
		elif com == "BEAC":
			beacon()

def login():
	global baseurl
	global username
	global jwt
	global login

	headers = {"Content-Type": "application/json; charset=utf-8"}
	url = baseurl + "/login"

	username = input("Username: ")
	password = input("Password: ")

	data = {
		"username": username,
		"password": password,
	}

	response = requests.post(url, headers=headers, json=data)
	if response.status_code == 200:
		print(response.status_code)
		login = True
		jwt = response.text
		print(jwt)
	else:
		print(response.status_code)
		print("login failed")

def beacon():
	global baseurl
	global jwt
	global login
	url = baseurl + "/beacon/register"
	headers = {
		"Content-Type": "application/json; charset=utf-8",
		"Authorization": "Bearer " + jwt,
	}

	if not login:
		print("Please login first") 
	else:
		response = requests.get(url, headers=headers)
		if response.status_code == 200:
			print("New Beacon Registered with token:")
			print(response.json())
		else:
			print(response.status_code)

			print("login failed")

def register():
	global baseurl
	headers = {"Content-Type": "application/json; charset=utf-8"}
	url = baseurl + "/register"
	
	username = input("Username: ")
	password = input("Password: ")

	data = {
		"username": username,
		"password": password,
	}

	response = requests.post(url, headers=headers, json=data)
	print(response.status_code)
	print(response.text)
	
def command():
	print("Command")

if __name__ == "__main__":
    main()