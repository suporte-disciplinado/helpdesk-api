FROM maven:3.9.9-eclipse-temurin-21-alpine

WORKDIR /app

FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY api-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
