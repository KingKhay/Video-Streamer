FROM eclipse-temurin:17-jdk-jammy as builder

WORKDIR /app

COPY .mvn/ .mvn

COPY mvnw pom.xml ./

RUN ./mvnw dependency:go-offline

COPY ./src ./src

RUN ./mvnw clean install

FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

USER ebenezer

EXPOSE 8080

COPY --from=builder /app/target/video_streamer.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
