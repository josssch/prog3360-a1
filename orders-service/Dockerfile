FROM maven:3.9.12-eclipse-temurin-17 AS build

WORKDIR /build

COPY mvnw mvnw.cmd pom.xml ./
COPY src/ src/
RUN mvn install
RUN mvn package

FROM eclipse-temurin:17 AS prod

WORKDIR /app

COPY --from=build /build/target/*.jar prod.jar
CMD ["java", "-jar", "prod.jar"]
