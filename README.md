# Spring Boot + MySQL + Docker

## Spring Boot Application with JPA
- Open https://start.spring.io/
- Create a Gradle App with `Web, MySQL, JPA` dependencies
- Extract it and import
- Create packages, Entity classes and add respective service, repository, controller
- Under resources delete all and create application.yml file. This will have all details related to MySQL and service port
- Test the controller by running the application using below steps,
  
  ### MySQL
  - Create database "company" else application start up will fail because we have configured the db name in `application.yml`. No need to create tables those will be created by JPA if not present. Learn about `@Entity`
  - `mysql -uroot -pdev`   (or what ever password is)
  - `mysql> create database company;`
  - `mysql> use company;`

  ### Spring-boot App
  - `$ cd to project`
  - We will name our jar with a meaningful name. Set name in `settings.gradle` file and in `build.gradle` remove version or give say 1 or 2
  - `$ ./gradlew clean build`
  - `$ ./gradlew bootRun` (if you want to run in debug mode, `$ ./gradlew bootRun --debug-jvm` and then create a remote-debug in Edit configurations at port like 5005)
  - Run, POST and GET calls to test the application using http://localhost:8200/company/employees

## Docker file
- Create docker file "Dockerfile" under project root
- We need jdk so take it as base and then copy the jar file. Since we don't upload build folder to github, move it out and put it under root folder
- Expose the server port which we are using in the `application.yml` file
- NOTE: We will need to MySQL too running before testing this image. Lets do that.

## Attention
- Before moving to deployment to docker we need to do following steps,
  - The mysql url should in `application.yml` file should point to the "docker" mysql host name which we mention using --name
  - It is always good to have a password to mysql. So give password if not present in application.yml file
  - Two containers within the docker cannot communicate without being on the same network. So create a network say, `$ docker network create spring-app-mysql-ntw`

## Testing Docker Image
- `$ docker network create spring-app-mysql-ntw`
- `$ docker run --name database --network spring-app-mysql-ntw -e MYSQL_ROOT_PASSWORD=dev -e MYSQL_DATABASE=company -d mysql:5.6`
  ### Here,
  - `database` is the name of the container .. this should be mentioned in the mysql url of spring-boot `application.yml` file
  - `spring-app-mysql-ntw` is also very imp bcoz without this sping-appt cannot communicate with the mysql container
- Build: `$ cd to project && docker build -t spring-boot-mysql-app .`
- Run: `$ docker run --name spring-boot-jpa-app --network spring-app-mysql-ntw -d -p 8200:8200 spring-boot-mysql-app`
- Hit: **POST:** `http://localhost:8200/company/employees/all` and give "raw" input of type JSON as,
  ```
  [
    {
      "firstName": "amarnath",
      "lastName": "chandana",
      "email": "amarnath@docker.com"
    },
    {
      "firstName": "prasad",
      "lastName": "chandana",
      "email": "prasad@docker.com"
    }
  ]
  ```
- Hit: **GET:** `http://localhost:8200/company/employees` should see above users


