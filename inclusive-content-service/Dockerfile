FROM eclipse-temurin:17-alpine

COPY build/libs/inclusive-content-service.war /app/app.war

CMD ["java", "-jar", "-Dspring.profiles.active=dev", "/app/app.war"]
