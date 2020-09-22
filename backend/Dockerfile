FROM openjdk:14.0.2-jdk-oracle
WORKDIR /
ADD target/backend-0.0.1-SNAPSHOT.jar //
EXPOSE 8080
ENTRYPOINT [ "java", "-Dspring.profiles.active=mysql", "-jar", "/backend-0.0.1-SNAPSHOT.jar"]
