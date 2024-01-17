FROM maven:3.8.4-openjdk-17 AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean install -DskipTests=true

FROM openjdk:17-oracle

COPY --from=builder /app/target/java-test-configuration-poc-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]