FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/ecommerce-mmr-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app_ecommerce.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_ecommerce.jar"]