# Étape 1 : Utilisation d'une image de base Java
FROM openjdk:17-jdk-slim as builder

# Étape 2 : Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Étape 3 : Copier le fichier JAR généré dans le conteneur
# (Remplacez `target/paimentservice.jar` par le chemin réel vers votre fichier JAR)
COPY target/paiment-service-0.0.1-SNAPSHOT.jar payment.jar

# Étape 4 : Exposer le port utilisé par le service
EXPOSE 9091

# Étape 5 : Définir la commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "payment.jar"]
