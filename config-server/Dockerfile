# Step 1: Build the application using Maven
FROM maven:3.9.4-eclipse-temurin-17 AS build

# Set the working directory
WORKDIR /app

# Copy the project files to the working directory
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Step 2: Create the runtime image
FROM openjdk:17-slim
# Set the working directory
WORKDIR /app

# Copy the built jar file from the build stage
COPY --from=build /app/target/config-server-0.0.1-SNAPSHOT.jar config-server.jar

# Expose the application's port
EXPOSE 8888

# Command to run the application
ENTRYPOINT ["java", "-jar", "config-server.jar"]
