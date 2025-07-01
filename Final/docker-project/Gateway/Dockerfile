FROM eclipse-temurin:21-jdk

RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app
COPY . .

RUN mvn clean install -DskipTests

CMD ["mvn", "spring-boot:run"]
