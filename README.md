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
        - Username (Type: String)
        - Password (Type: String)
    - Sample body request { “username”: “John Smith”, “password”: ”12345678”}

- /register
    - POST
    - Description: Register a user by sending new login credentials consisting of a username and password
    - Fields:
        - Username (Type: String)
        - Password (Type: String)
    - Sample body request { “username” : “John Smith”, “password”: “12345678”}

- /beacon/create
    - POST
    - Description: Takes in a username. Creates a beacon associated with that username and auto-generates a beaconid.
    - Fields
        - username
    - Example: curl -X POST "localhost:8080/beacon/create?username=username"
        - Creates a beacon associated with user ‘username’

- /beacon/command
    - GET
    - Description: Receive a list of command objects from a beacon identified by a beaconid with a given status (optional).
    - Returns a list of commands objects and a 200 OK on success, 400 Bad Request on failure. A command object contains an integer identifier "id", an integer "beaconid" of the corresponding beacon, a user-defined string "content", and string "status" that is one of "pending", "sent", "executed", or "finished".
    - Fields
        - beaconid: a non-negative integer used to identify the beacon.
        - status: An optional argument specifying the status of the command. Can be one of "pending", "sent", "executed", or "finished". If no status is supplied, commands of any status are retrieved.
    - Example:
        - localhost:8080/beacon/command?beaconid=123456789
        - localhost:8080/beacon/command?beaconid=123456789&status=pending

- /beacon/command
    - POST
    - Description: send a list of command objects to a beacon
    - Returns an error message (string) and a 200 OK on invalid input, 200 OK and validation message on valid input. On correct input, we also insert the commands received into the “commands” database, with the relevant fields
    - Fields
        - commands: a CommandList, which is a class used to represent a list of command objects; contains all the commands we want to send. We retrieve this CommandList from the body of the POST mapping, which is in JSON form
    - Example:
        - Sample body request: { {"beaconid":123456789, “content”: “do nothing”}, {"beaconid":1234, “content”: “do something”}}

### COMMON ISSUES

If you get a JAVA_HOME error, it’s likely that the JAVA_HOME was auto set incorrectly. To fix, run 
```export JAVA_HOME=/usr/lib/jvm/msopenjdk-current```
