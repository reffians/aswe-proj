# How to set up VS Code Dev Containers
1. Download and install Docker Desktop.
2. Download and install VS Code.
3. Open the repo in a new workspace in VS Code.
4. Write a new file `.env` inside `.devcontainer` that contains:
    ```
    MYSQL_ROOT_PASSWORD=rootsecret
    MYSQL_HOST=db
    MYSQL_DATABASE=appdb
    MYSQL_USER=springuser
    MYSQL_PASSWORD=springusersecret
    ```
    Docker Compose will read the environment variables from this file, so we don't have to hardcode them into `docker-compose.yml`. The MySQL container creates a new database `appdb` and a new superuser for this database `springuser` with password `springusersecret`. The Spring Boot app is now configured (via `src/main/resources/application.properties`) such that it connects to the correct MySQL host (`db`) using `springuser` and `springusersecret` to access `appdb`.

5. Install Docker and Dev Containers extensions in VS Code - VS Code will prompt you to install all the necessary extensions.
6. Open in a Dev Container - VS Code will prompt you to do this. Otherwise, press F1, write "Dev Containers" and select something like: "Dev Containers: Reopen in Container" or "Dev Containers: Rebuild Container".



### Build, Run, Test instructions:
- Open our repo directory in a VSCode dev container (follow the README.md instructions).

- Build and Run
    - Run our application with maven using the command:
    - `./mvnw spring-boot:run`
- Test
    - To run our unit tests use the command:
        - `./mvnw test`
        
### Style Checker:
- We used the Checkstyle style checker with the google coding style specifications for Java.
- To run our style checker, run:
    - `./mvnw checkstyle:check`
- Our code is Checkstyle clean. Refer to below:
    - checkstyle-output-10/26/2022

### API DOCUMENTATION
- /login
    - POST
    - Description: Login with user credentials consisting of a username and password
    - Fields:
        A JSON containing the following.
        - Username (Type: String)
        - Password (Type: String)
    - Sample body: { “username”: “John Smith”, “password”: ”12345678”}

- /register
    - POST
    - Description: Register a user by sending new login credentials consisting of a username and password
    - Fields:
        A JSON containing the following.  
        - Username (Type: String)
        - Password (Type: String)
    - Sample body: { “username” : “John Smith”, “password”: “12345678”}

- /beacon/register
    - POST
    - Description: Requires JWT token. Register a beacon associated with that username and auto-generates a beaconid.
    - Fields
        - None. <implicit in token authorization header>
    - Sample header: headers = {
		"Content-Type": "application/json; charset=utf-8",
		"Authorization": "Bearer " + jwt,
	}
        - Registers a beacon associated with JWT token username

- /beacon/command
    - POST
    - Description: Receive a list of command objects from a beacon identified by a beaconid with a given status (optional).
    - Returns a list of commands objects and a 200 OK on success, 400 Bad Request on failure. A command object contains an integer identifier "id", an integer "beaconid" of the corresponding beacon, a user-defined string "content", a "type" (STOP|SLEEP|EXECUTE|DOWNLOAD|GETHOSTNAME|GETHOSTOS) which corresponds to commandType, boolean "has_been_sent" which marks if the command has been sent, and "time_sent" which has a timestamp of when the command was sent to the server.
    - Fields
        - A JSON containing a beacon object: <beaconid, token> consists of a non-negative integer used to identify the beacon, a btoken.
    - Sample body:
       {
			"beacon": {
				"id": bid, 
				"token": btoken,
			},
		}

- /beacon/result
    - POST
    - Description:  send results for a beacon. Returns 200 OK and an array of Command objects on success, 400 Bad Request with an error message on failure.
    - Returns a list of command objects. A command object contains integer "id" which corresponds to commandid, integer "beaconid" of the corresponding beacon, beacon-defined string "content" which corresponds to command output, boolean "has_been_sent" which marks if the command has been sent, and "time_sent" which has a timestamp of when the command was sent.
    - Fields
        - A JSON containing a request object consisting of a beacon and a list of results which include exec_time, commandid, content.
    - Sample body: 
    {'beacon': {'id': '1', 'token': 'IlQk6xa5DlfqWlHdOf_lU_lZ-Gv6doyBEyWt3qM9gXArkExFMFEwx2aNE8caUmlDCilYWMfWi7vzyI0Zq7Sy5g'}, 'results': [{'commandid': 3, 'content': 'asheets-mbp-2.lan\n', 'exec_time': '2022-12-05T00:39:33'}, {'commandid': 6, 'content': '', 'exec_time': '2022-12-05T00:39:33'}]}

- /user/result
    - POST
    - Description: Requires JWT authentication. Confirms user received results.
    - Returns ResponseEntity with HttpStatus 300 Created on success, and 400 Bad Request on failure.
    - Fields:
        - None. <implicit in token authorization header> 
    - Sample header: 
        {
		"Content-Type": "application/json; charset=utf-8",
		"Authorization": "Bearer " + jwt,
	    }

- /user/command
    - POST
    - Description: send a list of command strings to a beacon
    - Returns an error message (string) and a 400 Bad Request on invalid input, 201 Created and validation message on valid input.
    - Fields
        A list of JSON objects containing the following.
        - beaconid: a non-negative integer used to identify the beacon.
        - content: a string used to represent command arguments.
        - commandType: a type from (STOP|SLEEP|EXECUTE|DOWNLOAD|GETHOSTNAME|GETHOSTOS)
    - Sample body:
        data = [{
		"beaconid": 1,
		"commandType": DOWNLOAD,
		"content": http://abc.com/x.sh,
	}]
    
### CLIENT INSTRUCTIONS
    Note: Beacon runs on Mac
    
    Beacon
        Usage: $ python3 beacon.py <id> <beacon token> <baseurl>
    
    Client
        Usage: $ python3 client.py <baseurl>

### CI Reports
CI is implemented through CircleCI. Reports are located here (https://app.circleci.com/pipelines/github/reffians/aswe-proj) and include branch coverage reports.   

### COMMON ISSUES

If you get a JAVA_HOME error, it’s likely that the JAVA_HOME was auto set incorrectly. To fix, run 
```export JAVA_HOME=/usr/lib/jvm/msopenjdk-current```
