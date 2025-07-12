FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY target/SleepTrackerSOAPSpringService-0.0.1-SNAPSHOT.jar SleepTrackerSOAPSpringService-0.0.1-SNAPSHOT.jar
COPY data data

EXPOSE 8082

CMD ["java", "-jar", "SleepTrackerSOAPSpringService-0.0.1-SNAPSHOT.jar"]