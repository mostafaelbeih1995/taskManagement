FROM openjdk:17
COPY target/*.jar /app.jar
VOLUME ["/var/lib/bankMisr" , "/var/lib/bankMisr/temp"]
ENTRYPOINT ["java","-jar","/app.jar"]