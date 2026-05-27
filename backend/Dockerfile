FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /workspace
COPY pom.xml mvnw .mvn/ ./
# RUN mvn dependency:go-offline -B
COPY src src
RUN mvn -B -DskipTests package

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /workspace/target/finance-0.0.1-SNAPSHOT.jar /app/finance.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/finance.jar"]
