# Use official Java runtime

FROM openjdk:17-jdk-slim

# Set working directory

WORKDIR /app

# Copy Maven wrapper and pom.xml

COPY mvnw .
COPY mvnw.cmd .
COPY .mvn .mvn
COPY pom.xml .

# Download dependencies

RUN ./mvnw dependency:go-offline -B

# Copy source code

COPY src ./src

# Build the application

RUN ./mvnw package -DskipTests

# Run the jar file

CMD ["java","-jar","target/RandomIdeaGenerator-0.0.1-SNAPSHOT.jar"]

# Expose Spring Boot port

EXPOSE 8080
