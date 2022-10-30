#!/bin/sh
openssl rand -base64 64 | tr -dc A-Za-z0-9 > mysql_password.txt
openssl rand -base64 64 | tr -dc A-Za-z0-9 > mysql_root_password.txt