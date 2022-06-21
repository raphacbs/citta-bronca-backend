FROM adoptopenjdk/openjdk16:x86_64-alpine-jdk-16.0.1_9-slim
ARG JAR_FILE=target/cittabronca-backend-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
#ADD target/cittabronca-backend-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]