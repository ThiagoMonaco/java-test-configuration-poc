FROM openjdk:17-oracle

COPY target/java-test-configuration-poc-0.0.1-SNAPSHOT.jar /app.jar

ENTRYPOINT ["java","-jar","/app.jar"]