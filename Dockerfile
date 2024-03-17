FROM openjdk:17
COPY target/*.jar /app.jar
VOLUME ["/var/lib/stc" , "/var/lib/stc/temp"]
ENTRYPOINT ["java", "-jar", "/app.jar"]