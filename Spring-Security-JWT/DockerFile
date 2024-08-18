# Usa una imagen base de OpenJDK
FROM openjdk:17-jdk-slim



# Copiar el archivo .jar al contenedor
COPY  target/Spring-Security-JWT-0.0.1-SNAPSHOT.jar app_back.jar



# Configurar el punto de entrada para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app_back.jar"]
