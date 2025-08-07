# Use a lightweight, secure OpenJDK base image
FROM eclipse-temurin:17-jre-ubi9-minimal

# Set working directory
WORKDIR /app

# Argument to pass in the JAR file (useful in Jenkins)
ARG JAR_FILE=target/*.jar

# Copy the JAR file into the image
COPY ${JAR_FILE} app.jar

# Expose the application port
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]

# Metadata
LABEL authors="rohithkodipaka"
