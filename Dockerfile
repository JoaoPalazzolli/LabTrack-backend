FROM maven:3.9.6 AS BUILD

WORKDIR /usr/src/labtrack

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:17

WORKDIR /app

COPY --from=BUILD /usr/src/labtrack/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=prod"]