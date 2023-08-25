# Etapa de compilación
FROM gradle:7.2.0-jdk17 AS build

# Configurar el directorio de trabajo
WORKDIR /app

# Copiar los archivos de configuración y dependencias Gradle
COPY build.gradle settings.gradle ./
COPY src src

# Ejecutar el comando de compilación
RUN gradle build --no-daemon


# Etapa de ejecución
FROM eclipse-temurin:17

# Copiar el archivo JAR compilado desde la etapa de compilación
COPY --from=build /app/build/libs/ramr-pizzeria-0.0.1-SNAPSHOT.jar /app/ramr-pizzeria-0.0.1-SNAPSHOT.jar

# Exponer el puerto
EXPOSE 8080

# Comando de ejecución
ENTRYPOINT ["java", "-jar", "/app/ramr-pizzeria-0.0.1-SNAPSHOT.jar"]