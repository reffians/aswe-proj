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