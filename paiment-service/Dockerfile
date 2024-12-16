# Step 1: Build the application using Maven with Java 17
FROM maven:3.9.4-eclipse-temurin-17 AS build

# Set the working directory
WORKDIR /app

# Copy the project files to the working directory
COPY pom.xml .

# Download the dependencies (this step will be cached unless pom.xml changes)
RUN mvn dependency:go-offline

# Copy the source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Step 2: Use the Java image to run the application
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/paiment-service-0.0.1-SNAPSHOT.jar payment-service.jar

# Expose the application port
EXPOSE 9091

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "payment-service.jar"]
