FROM openjdk:17-jdk

WORKDIR /app

COPY target/fibonacci-api-1.0-SNAPSHOT.jar /app/fibonacci-api-1.0-SNAPSHOT.jar

EXPOSE 8000

CMD ["java", "-jar", "fibonacci-api-1.0-SNAPSHOT.jar"]