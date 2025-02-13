# Étape 1 : Utiliser Maven avec OpenJDK 21 pour builder l'application
FROM maven:3.9.4-eclipse-temurin-21 AS builder

# Copier les fichiers Maven et le code source
COPY pom.xml .
COPY /src ./src

# Télécharger les dépendances et compiler l’application
RUN mvn clean install -DskipTests

# Étape 2 : Utiliser une image OpenJDK 21 légère pour exécuter l’application
FROM eclipse-temurin:21-jdk

# Copier le fichier JAR compilé depuis le conteneur builder
COPY --from=builder /target/ccd-1.jar ccd-1.jar

# Exposer le port utilisé par l'application (modifie si besoin)
EXPOSE 8080

# Démarrer l'application
CMD ["java", "-jar", "ccd-1.jar","--spring.profiles.active=integration"]
