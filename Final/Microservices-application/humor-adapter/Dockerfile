# Use the official Eclipse Temurin image with Java 21 JDK
FROM eclipse-temurin:21-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled JAR file into the container
COPY target/UmorSOAPAdapter-0.0.1-SNAPSHOT.jar UmorSOAPAdapter-0.0.1-SNAPSHOT.jar

# Expose port 8088 to allow external access
EXPOSE 8088

# Run the application using the java -jar command after the build of container
CMD ["java", "-jar", "UmorSOAPAdapter-0.0.1-SNAPSHOT.jar"]