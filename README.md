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
  - `mysql -uroot -p`   (or what ever password is)
  - `mysql> create database company;`
  - `mysql> use company;`

  ### Spring-boot App
  - `$ cd to project`
  - We will name our jar with a meaningful name. Set name in `settings.gradle` file and in `build.gradle` remove version or give say 1 or 2
  - `$ ./gradlew clean build`
  - `$ ./gradlew bootRun` (if you want to run in debug mode, `$ ./gradlew bootRun --debug-jvm` and then create a remote-debug in Edit configurations at port like 5005)
  - Run, POST and GET calls to test the application

## Docker file
- Create docker file "Dockerfile" under project root
- We need jdk so take it as base and then copy the jar file. Since we don't upload build folder to github, move it out and put it under root folder
- Expose the server port which we are using in the `application.yml` file
- NOTE: We will need to MySQL too running before testing this image. Lets do that.

## Testing Image


