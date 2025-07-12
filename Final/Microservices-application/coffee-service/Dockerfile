# Usa Tomcat 11 con Java 21
FROM tomcat:11-jdk21

# Espone porta 80
EXPOSE 8080

# Rimuove la webapp ROOT di default
RUN rm -rf /usr/local/tomcat/webapps/*

# Copia il .war nella cartella webapps
COPY target/CoffeeTrackerRESTServiceMaven.war /usr/local/tomcat/webapps/CoffeeTrackerRESTServiceMaven.war
COPY data /usr/local/tomcat/webapps/data