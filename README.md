# How to set up VS Code Dev Containers
1. Download and install Docker Desktop.
2. Download and install VS Code.
3. Open the repo in a new workspace in VS Code.
4. Write a new file `db_vars.env` inside `.devcontainer` that contains:
```
MYSQL_HOST=mysql
MYSQL_USER=root
MYSQL_PASSWORD=secret
MYSQL_DB=appdb
MYSQL_ROOT_PASSWORD=secret
MYSQL_DATABASE=appdb
```
4. Install Docker and Dev Containers extensions in VS Code - VS Code will prompt you to install all the necessary extensions.
5. Open in a Dev Container - VS Code will prompt you to do this. Otherwise, press F1, write "Dev Containers" and select something like: "Dev Containers: Reopen in Container" or "Dev Containers: Rebuild Container".