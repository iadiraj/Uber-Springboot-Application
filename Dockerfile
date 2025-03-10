# Stage 1: Build the Maven project
FROM eclipse-temurin:17-jdk AS builder
WORKDIR /app
COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .
RUN ./mvnw dependency:go-offline
COPY /src ./src
RUN ./mvnw clean package -DskipTests

# Stage 2: Create the final image with OpenJDK JRE
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/uber.jar
EXPOSE 8080
CMD ["java", "-jar", "uber.jar"]

