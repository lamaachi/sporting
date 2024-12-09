# Use a base image with Java
FROM openjdk:17-slim

# Set the working directory
WORKDIR /app

# Copy the jar file to the container
COPY target/discovery-Service-0.0.1-SNAPSHOT.jar /app/discovery-service.jar

# Expose the port that the Eureka server will run on
EXPOSE 8761

# Run the application
ENTRYPOINT ["java", "-jar", "/app/discovery-service.jar"]
