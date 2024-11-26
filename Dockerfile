# Use an official Tomcat image
FROM tomcat:9-jdk11-openjdk

# Remove the default web apps to avoid conflicts
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy your WAR file into Tomcat's webapp directory
COPY target/todo-web-mvn.war /usr/local/tomcat/webapps/ROOT.war

# Expose the port Tomcat listens on
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
