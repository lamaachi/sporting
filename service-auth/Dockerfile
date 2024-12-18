
## Step 1: Build the application using a Maven image with Java 17
FROM maven:3.9.4-eclipse-temurin-17 AS build
#
## Set the working directory
WORKDIR /app
#
## Copy the project files to the working directory
COPY pom.xml .
#
## Download the dependencies (this step will be cached unless pom.xml changes)
RUN mvn dependency:go-offline
#
#
COPY src ./src
#
## Build the application
RUN mvn clean package -DskipTests
# Étape 1 : Utilisation d'une image de base Java

FROM openjdk:17-jdk-slim as builder

# Étape 2 : Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Étape 3 : Copier le fichier JAR généré dans le conteneur
# (Remplacez `target/paimentservice.jar` par le chemin réel vers votre fichier JAR)
#COPY target/service-auth-0.0.1-SNAPSHOT.jar auth.jar
COPY --from=build /app/target/service-auth-0.0.1-SNAPSHOT.jar auth.jar
# Étape 4 : Exposer le port utilisé par le service
EXPOSE 9090

# Étape 5 : Définir la commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "auth.jar"]
