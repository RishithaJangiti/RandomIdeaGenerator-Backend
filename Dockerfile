# Use official Java 17 image
FROM openjdk:17

# Set working directory
WORKDIR /app

# Copy the jar file
COPY target/*.jar app.jar

# Expose Spring Boot port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java","-jar","app.jar"]