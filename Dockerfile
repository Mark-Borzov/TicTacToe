FROM openjdk:17-jdk-slim
WORKDIR /app
COPY /build/libs/TicTacToe-0.0.1-SNAPSHOT.jar /app/TicTacToe.jar
ENTRYPOINT ["java", "-jar", "TicTacToe.jar"]