# Étape 1 : Construire l'application
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY /backend .
RUN mvn clean package -DskipTests

# Étape 2 : Exécuter l'application
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
