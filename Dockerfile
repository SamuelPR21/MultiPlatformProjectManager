# Usa una imagen base de Java
FROM openjdk:17-jdk-slim AS build

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo build.gradle y las dependencias
COPY build.gradle.kts ./
COPY settings.gradle.kts ./
COPY gradle gradle
COPY gradlew gradlew
RUN ./gradlew build --no-daemon

# Copia el código fuente
COPY src ./src


# Imagen final
FROM openjdk:17-jdk-slim

# Copia el JAR construido desde la imagen anterior
COPY --from=build /app/build/libs/demo-0.0.1-SNAPSHOT.jar app.jar

# Establece el puerto que escucha la aplicación
EXPOSE 9001

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app.jar"]
