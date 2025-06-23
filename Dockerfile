FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY build/libs/account-service-0.1-all.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]