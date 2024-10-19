FROM openjdk:17-jdk-slim
COPY build/libs/backend-0.0.1-SNAPSHOT.jar bbb.jar
ENTRYPOINT ["java", "-jar", "bbb.jar"]