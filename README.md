# Gestor de Alumnos

Este proyecto es un gestor de alumnos que permite administrar cursos y estudiantes. Proporciona funcionalidades como agregar estudiantes, asignar estudiantes a cursos, obtener información sobre los cursos y estudiantes, y calcular estadísticas relacionadas con los estudiantes.

## Tecnologías utilizadas

El proyecto está desarrollado utilizando las siguientes tecnologías:

- Java
- Spring Framework (Spring Boot, Spring MVC, Spring Data JPA, Validation)
- Lombok
- Maven

## Documentación de la API

La API está documentada utilizando Swagger. Podés acceder a la documentación de la API a través de la siguiente URL:

- [Swagger UI](http://localhost:8080/swagger-ui/index.html)

La documentación de Swagger proporciona detalles sobre los endpoints disponibles, los parámetros requeridos, las respuestas esperadas y más.

## Estructura del proyecto

El proyecto sigue una estructura de paquetes típica de una aplicación Spring Boot. A continuación se muestra la estructura básica:

- `com.alkemy.gestoralumnos.exceptions` : Contiene las clases de excepciones personalizadas utilizadas en la aplicación. Estas excepciones se lanzan en casos específicos de error y proporcionan información detallada sobre el tipo de error ocurrido.
- `com.alkemy.gestoralumnos.controllers`: Contiene los controladores de la API REST utilizados para manejar las solicitudes HTTP.
- `com.alkemy.gestoralumnos.dto`: Contiene las clases DTO (Data Transfer Object) utilizadas para transferir datos entre el frontend y el backend.
- `com.alkemy.gestoralumnos.services`: Contiene las interfaces de los servicios utilizados en la aplicación.
- `com.alkemy.gestoralumnos.services.impl`: Contiene las implementaciones de los servicios.
- `com.alkemy.gestoralumnos.repositories`: Contiene los repositorios de Spring Data JPA utilizados para acceder a la base de datos.
- `com.alkemy.gestoralumnos`: Paquete principal que contiene la clase principal `Application` y la configuración general de la aplicación.

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

1. Cloná el repositorio del proyecto.
2. Importá el proyecto en tu entorno de desarrollo (por ejemplo, IntelliJ, Eclipse).
3. Configurá la conexión a la base de datos si es necesario (ver archivo `application.properties`).
4. Ejecutá la clase `Application` como una aplicación Spring Boot.

La aplicación se ejecutará en el puerto predeterminado 8080. Podés acceder a la API REST utilizando herramientas como Postman o a través de un navegador web.
