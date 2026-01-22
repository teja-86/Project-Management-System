# ===== Build Stage =====
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

# ===== Run Stage =====
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy only the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port (Render sets PORT env automatically)
EXPOSE 8080

# Run with remote profile (you can override via env var if needed)
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=${SPRING_PROFILES_ACTIVE:-remote}"]
