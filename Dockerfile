FROM openjdk:17-jdk

WORKDIR /app

COPY target/fibonacci-api-1.0-SNAPSHOT.jar /app/fibonacci-api-1.0-SNAPSHOT.jar

# Expose the port your app runs on
EXPOSE 8000

# Command to run your application
CMD ["java", "-jar", "your-app.jar"]