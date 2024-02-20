FROM openjdk:21
EXPOSE 8180
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
LABEL authors="AVF"
ENTRYPOINT ["java", "-jar","app.jar"]