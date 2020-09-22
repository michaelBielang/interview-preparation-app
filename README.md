# Introduction

This repository is part of my upcoming youtube series on how to build a microservice architecture with a simple backend and frontend.

This demo can give you an idea how to properly structure and setup a project. In my video sessions I also demonstrate some common mistakes as well how to avoid them.

In the end you will have an app which you can use with a provided collection of questions and answers.

These you can use and extend to prepare for interviews. 

These are commonly asked questions, which make sometimes more or less sense in the dailys business ;)
## App 

[![js-standard-style](https://img.shields.io/badge/code%20style-standard-brightgreen.svg?style=flat)]()

## Features

 * [x] Backend with Spring Boot and Java 14
 * [x] JWT Auth.
 * [x] Angular
 * [x] H2/MySQL DB with liquibase
 * [x] Tests
 * [x] A basic set of questions/answers
 
## Todos for further versions

 - Implement React on another microservice instant to offer two different UIs for the same backend

## Installation

* Docker CE/EE with Hyper-V
* Java 14
* If you use Windows 10 Pro ensure WSL2 is installed https://docs.microsoft.com/en-us/windows/wsl/install-win10

## Execute

##### Run Docker container with docker profile set in Dockerfile and migration scripts on host

``
docker run  --detach   --name internal-mysql -p 6604:3306 -e MYSQL_ROOT_PASSWORD=user_pass -e MYSQL_DATABASE=internal -e MYSQL_USER=user -e MYSQL_PASSWORD=user_pass mysql``
``

##### Interact with Database (link to ec-mysql container) with mysql client
``
docker run -it --link internal-mysql:mysql --rm mysql sh -c 'exec mysql -h"$MYSQL_PORT_3306_TCP_ADDR" -P"$MYSQL_PORT_3306_TCP_PORT" -uroot -p"$MYSQL_ENV_MYSQL_ROOT_PASSWORD"'
``

docker run --name internal-backend -p 8080:8080  --link internal-mysql:mysql -d backend

docker run -P codemerger:0.0.1-SNAPSHOT -p 8080:8080 --link internal-mysql:mysql -d backend

##### Further commands

``
docker log nameOfTheContainer
``
