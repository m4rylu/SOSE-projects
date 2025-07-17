# Use Tomcat 11 with Java 21
FROM tomcat:11-jdk21

# Use custom server configuration
COPY server.xml /usr/local/tomcat/conf/server.xml

# Expose the application port
EXPOSE 8085

# Remove default ROOT application
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the WAR file into the webapps folder
COPY target/UmorTrackerSOAPServiceMaven.war /usr/local/tomcat/webapps/UmorTrackerSOAPServiceMaven.war

# Copy the data folder into the container
COPY data /usr/local/tomcat/webapps/data