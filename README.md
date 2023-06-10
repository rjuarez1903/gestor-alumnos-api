# Gestor de Alumnos

Este proyecto es un gestor de alumnos que permite administrar cursos y estudiantes. Proporciona funcionalidades como agregar estudiantes, asignar estudiantes a cursos, obtener información sobre los cursos y estudiantes, y calcular estadísticas relacionadas con los estudiantes.

## Tecnologías utilizadas

El proyecto está desarrollado utilizando las siguientes tecnologías:

- Java
- Spring Framework (Spring Boot, Spring MVC)
- Lombok
- Maven

## Documentación de la API

La API está documentada utilizando Swagger. Puedes acceder a la documentación de la API a través de la siguiente URL:

- [Swagger UI](http://localhost:8080/swagger-ui/index.html)

La documentación de Swagger proporciona detalles sobre los endpoints disponibles, los parámetros requeridos, las respuestas esperadas y más.

## Estructura del proyecto

El proyecto sigue una estructura de paquetes típica de una aplicación Spring Boot. A continuación se muestra la estructura básica:

- com.alkemy.gestoralumnos.controllers: Contiene los controladores de la API REST utilizados para manejar las solicitudes HTTP.
- com.alkemy.gestoralumnos.dto: Contiene las clases DTO (Data Transfer Object) utilizadas para transferir datos entre el frontend y el backend.
- com.alkemy.gestoralumnos.services: Contiene las interfaces de los servicios utilizados en la aplicación.
- com.alkemy.gestoralumnos.services.impl: Contiene las implementaciones de los servicios.
- com.alkemy.gestoralumnos: Paquete principal que contiene la clase principal Application y la configuración general de la aplicación.

## Funcionalidades principales

El proyecto ofrece las siguientes funcionalidades principales:

- Obtener todos los cursos.
- Obtener los alumnos de un curso.
- Calcular el promedio de edad de los alumnos de un curso.
- Obtener los alumnos con la nota más alta de un curso.
- Agregar un alumno a un curso.
- Obtener todos los estudiantes.
- Obtener información sobre un estudiante específico.
- Obtener estudiantes morosos (que adeudan matrícula).
- Obtener estudiantes deudores de materias.

## Configuración y ejecución

1. Clona el repositorio del proyecto.
2. Importa el proyecto en tu entorno de desarrollo (por ejemplo, IntelliJ, Eclipse).
3. Configura la conexión a la base de datos si es necesario (ver archivo application.properties).
4. Ejecuta la clase `Application` como una aplicación Spring Boot.

La aplicación se ejecutará en el puerto predeterminado 8080. Puedes acceder a la API REST utilizando herramientas como Postman o a través de un navegador web.
