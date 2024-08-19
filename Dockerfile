# Stage 1: Build the application
FROM maven:3.9.8-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:21-ea-1-slim
WORKDIR /app
COPY --from=build /app/target/*.jar /app
EXPOSE 8080
CMD ["java", "--enable-preview", "-jar", "MinioObjectStorageService.jar"]