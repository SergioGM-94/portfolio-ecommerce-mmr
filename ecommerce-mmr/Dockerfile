#FROM openjdk:17-jdk-slim
#ARG JAR_FILE=target/ecommerce-mmr-0.0.1-SNAPSHOT.jar
#COPY ${JAR_FILE} app_ecommerce.jar
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "app_ecommerce.jar"]

FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/ecommerce-mmr-0.0.1-SNAPSHOT.jar ecommerce-mmr.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "ecommerce-mmr.jar"]