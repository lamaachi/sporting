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

# Use a base image with Java
FROM openjdk:17-slim

# Set the working directory
WORKDIR /app

# Copy the jar file to the container
COPY --from=build /app/target/discovery-Service-0.0.1-SNAPSHOT.jar /app/discovery-service.jar

# Expose the port that the Eureka server will run on
EXPOSE 8761

# Run the application
ENTRYPOINT ["java", "-jar", "/app/discovery-service.jar"]
