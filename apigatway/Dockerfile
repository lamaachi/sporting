# Step 1: Build the application using a Maven image with Java 17
FROM maven:3.9.4-eclipse-temurin-17 AS build

# Set the working directory
WORKDIR /app

# Copy the project files to the working directory
COPY pom.xml .

# Download the dependencies (this step will be cached unless pom.xml changes)
RUN mvn dependency:go-offline


COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the application's JAR file
COPY target/apigatway-0.0.1-SNAPSHOT.jar api-gateway.jar

# Expose the port on which the gateway listens
EXPOSE 8082

# Run the JAR file
ENTRYPOINT ["java", "-jar", "api-gateway.jar"]
