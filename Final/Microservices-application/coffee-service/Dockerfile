# Use Tomcat 11 with Java 21
FROM tomcat:11-jdk21

# Espose the application port 8080
EXPOSE 8080

# Remove default ROOT application
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the WAR file into the webapps folder
COPY target/CoffeeTrackerRESTServiceMaven.war /usr/local/tomcat/webapps/CoffeeTrackerRESTServiceMaven.war

# Copy the data folder into the container
COPY data /usr/local/tomcat/webapps/data