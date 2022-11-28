jwt = ""
login = False
username = ""
import requests


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
	global username
	username = input("Username: ")
	password = input("Password: ")

def beacon():
	if not login:
		print("Please login first") 

def register():
	username = input("Username: ")
	password = input("Password: ")

def command():
	print("Command")

if __name__ == "__main__":
    main()