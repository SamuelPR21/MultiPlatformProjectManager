# Usar una imagen base de Gradle para construir la aplicación
FROM gradle:8.10.2-jdk17 AS build
WORKDIR /app

# Copiar el código fuente y compilar la aplicación
COPY --chown=gradle:gradle . /app
RUN gradle build -x test

# Usar una imagen base de OpenJDK para ejecutar la aplicación
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copiar el archivo JAR desde la etapa de construcción
COPY --from=build /app/build/libs/*.jar app.jar

# Exponer el puerto en el que la aplicación se ejecutará
EXPOSE 9010

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]