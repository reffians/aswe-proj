import requests
import sys

btoken = ""

def main(argv):
	if len(argv) != 2:
		print("Usage: $ python3 beacon.py <beacon token>")
	else:
		btoken = argv[1]
		print(btoken)
		while True:
			#retreive commands
			#process commands
			#execute commands
			#send back commands

if __name__ == "__main__":
    main(sys.argv)