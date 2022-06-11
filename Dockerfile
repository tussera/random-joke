FROM openjdk:11-jre-slim
COPY build/libs/*SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]