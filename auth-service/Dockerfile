FROM openjdk:17-jdk-slim
LABEL authors="tarik"
WORKDIR /app
COPY target/registry-service-0.0.1-SNAPSHOT.jar register.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "register.jar"]

