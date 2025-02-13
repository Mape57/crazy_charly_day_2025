FROM openjdk:21
COPY target/ccd-1.jar ccd-1.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "ccd-1.jar", "--spring.profiles.active=integration"]