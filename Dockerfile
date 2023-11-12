FROM openjdk:20-jdk-slim
WORKDIR /app
COPY target/silownia-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java","-jar","silownia-0.0.1-SNAPSHOT.jar"]