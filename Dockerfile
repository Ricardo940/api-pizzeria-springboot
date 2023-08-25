# Usar la imagen de OpenJDK para compilar
FROM eclipse-temurin:17 AS build

# Copiar el código fuente y las dependencias al contenedor
WORKDIR /app
COPY build.gradle .
COPY src src
COPY gradlew gradlew
RUN ./gradlew build

# Etapa de ejecución
FROM eclipse-temurin:17

# Copiar el archivo JAR compilado desde la etapa de compilación
COPY --from=build /app/build/libs/ramr-pizzeria-0.0.1-SNAPSHOT.jar /app/ramr-pizzeria-0.0.1-SNAPSHOT.jar

# Exponer el puerto
EXPOSE 8080

# Comando de ejecución
ENTRYPOINT ["java", "-jar", "/app/ramr-pizzeria-0.0.1-SNAPSHOT.jar"]