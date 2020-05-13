FROM openjdk:8-jdk

ADD ./spring-boot-mysql.jar ./spring-boot-mysql.jar

EXPOSE 8200

ENTRYPOINT ["java", "-jar", "spring-boot-mysql.jar"]

