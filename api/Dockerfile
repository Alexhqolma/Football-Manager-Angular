# AS <NAME> to name this stage as maven
FROM maven:3-eclipse-temurin-17 AS maven
WORKDIR /usr/src/app
COPY . /usr/src/app
# Compile and package the application to an executable JAR
RUN mvn package -Dmaven.test.skip


# For Java 17,
FROM openjdk:17-ea-slim-buster
ARG JAR_FILE=app.jar
WORKDIR /opt/app
# Copy the spring-boot-api-tutorial.jar from the maven stage to the /opt/app directory of the current stage.
COPY --from=maven /usr/src/app/target/${JAR_FILE} /opt/app/
ENTRYPOINT ["java","-jar","app.jar"]
