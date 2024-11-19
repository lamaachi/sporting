# Step 1: Build the application using a Maven image with Java 21
FROM maven:3.9.0-openjdk-21-slim AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and the source code to the container
COPY pom.xml .
COPY src ./src

# Build the application (this will create the jar file in target/)
RUN mvn clean package -DskipTests

# Step 2: Run the application using a Java 21 runtime image
FROM openjdk:21-slim

# Set the working directory
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/centre-service-0.0.1-SNAPSHOT.jar centre-service.jar

# Expose the application port (adjust if different)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "centre-service.jar"]
