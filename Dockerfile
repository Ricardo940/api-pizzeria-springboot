FROM openjdk:17
EXPOSE 8080
RUN mkdir -p /app/
ADD build/libs/ramr-pizzeria-0.0.1-SNAPSHOT.jar /app/ramr-pizzeria-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/ramr-pizzeria-0.0.1-SNAPSHOT.jar"]