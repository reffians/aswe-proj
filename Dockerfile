FROM amazoncorretto:17-alpine-jdk AS build
WORKDIR /app
COPY . .
RUN ./mvnw package -Dmaven.test.skip

FROM amazoncorretto:17-alpine-jdk
ARG JAR_FILE=/app/target/*.jar
ARG PROPERTIES_FILE=/app/src/main/resources/application-prod.properties
# RUN addgroup -S spring && adduser -S spring -G spring # must run as root to bind to 80 (no reverse proxy)
# USER spring:spring
COPY --from=build ${PROPERTIES_FILE} application.properties
COPY --from=build ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar", "spring.config.location=application.properties"]