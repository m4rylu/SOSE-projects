# Usa Tomcat 11 con Java 21
FROM tomcat:11-jdk21

# Usa impostazioni personalizzate per il server
COPY server.xml /usr/local/tomcat/conf/server.xml

# Espone porta 
EXPOSE 8081

# Rimuove la webapp ROOT di default
RUN rm -rf /usr/local/tomcat/webapps/*

# Copia il .war nella cartella webapps
COPY target/AdviceCoffeeRESTProsumerService.war /usr/local/tomcat/webapps/AdviceCoffeeRESTProsumerService.war
