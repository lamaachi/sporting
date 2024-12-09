# Step 2: Create the runtime image
FROM openjdk:17-slim

# Install curl
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

# Set the working directory
WORKDIR /app

# Copy the built jar file from the build stage
#COPY --from=build /app/target/centre-service-0.0.1-SNAPSHOT.jar centre-service.jar
COPY /target/admin-server-0.0.1-SNAPSHOT.jar admin-service.jar

# Expose the application's port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "admin-service.jar"]