FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY target/*.jar restaurant-be-0.0.1-SNAPSHOT.jar

EXPOSE 8085

CMD ["java", "-jar", "restaurant-be-0.0.1-SNAPSHOT.jar"]