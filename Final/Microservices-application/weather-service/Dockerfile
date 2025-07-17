# Use the official Eclipse Temurin image with Java 21 JDK
FROM eclipse-temurin:21-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled JAR file into the container
COPY target/WeatherTrackerRESTSpring-0.0.1-SNAPSHOT.jar WeatherTrackerRESTSpring-0.0.1-SNAPSHOT.jar

# Copy the data folder into the container
COPY data data

# Expose port 8086 to allow external access
EXPOSE 8086

# Run the application using the java -jar command after the build of container
CMD ["java", "-jar", "WeatherTrackerRESTSpring-0.0.1-SNAPSHOT.jar"]