FROM openjdk:17

WORKDIR /ac2

COPY target/*.jar /ac2/ac2-0.0.1-SNAPSHOT.jar

EXPOSE 8585

CMD ["java", "-jar", "ac2-0.0.1-SNAPSHOT.jar"]