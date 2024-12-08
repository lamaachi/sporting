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

# Step 2: Create the runtime image
FROM openjdk:17-slim

# Set the working directory
WORKDIR /app

# Copy the built jar file from the build stage
COPY --from=build /app/target/terain-service-0.0.1-SNAPSHOT.jar terain-service.jar
#COPY /target/terain-service-0.0.1-SNAPSHOT.jar terain-service.jar

# Expose the application's port
EXPOSE 8084

# Command to run the application
ENTRYPOINT ["java", "-jar", "terain-service.jar"]
