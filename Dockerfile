FROM amazoncorretto:17

# Set the working directory in the container
WORKDIR /app

# Copy the Spring Boot JAR file into the container
COPY build/libs/TAFDataStore.jar app.jar

# Expose the port your application runs on (optional but recommended)
EXPOSE 8081

# Set the command to run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]