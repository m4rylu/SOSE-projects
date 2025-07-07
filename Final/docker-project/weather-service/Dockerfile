FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY target/WeatherTrackerRESTSpring-0.0.1-SNAPSHOT.jar WeatherTrackerRESTSpring-0.0.1-SNAPSHOT.jar
COPY data data
EXPOSE 8086

CMD ["java", "-jar", "WeatherTrackerRESTSpring-0.0.1-SNAPSHOT.jar"]