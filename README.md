# 📚 Literalura

[![Java](https://img.shields.io/badge/Java-17+-blue.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15+-blue.svg)](https://www.postgresql.org/)
[![Build](https://img.shields.io/badge/build-passing-brightgreen.svg)]()
[![License](https://img.shields.io/badge/license-MIT-lightgrey.svg)](LICENSE)

> **Literalura** es una aplicación de consola y API REST para la gestión y consulta de libros, autores y estadísticas, integrando la API de Gutendex y una base de datos PostgreSQL.

---

## 🚀 Características principales

- 🔎 **Buscar libros por título** (API Gutendex)
- 📚 **Listar libros registrados** en la base de datos
- ✍️ **Listar autores únicos**
- 🧓 **Listar autores vivos en un año específico**
- 🌎 **Listar libros por idioma**
- 📊 **Estadísticas de descargas** (total, promedio, máximo, mínimo)
- 🏆 **Top 10 libros más descargados**
- 👤 **Buscar libros por nombre de autor**
- 🗓️ **Listar autores por año de nacimiento**

---

## 🛠️ Tecnologías utilizadas

- Java 17+
- Spring Boot 3.5.0
- PostgreSQL
- Maven
- JUnit & Mockito (para tests)

---

## ⚡ Instalación rápida

1. **Clona el repositorio:**

   ```sh
   git clone https://github.com/ariel323/challenge-literalura.git
   cd challenge-literalura
   ```

2. **Configura la base de datos PostgreSQL:**

   - Crea la base de datos:
     ```sql
     CREATE DATABASE literalura;
     ```
   - Edita `src/main/resources/application.properties` con tus credenciales:
     ```
     spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
     spring.datasource.username=TU_USUARIO
     spring.datasource.password=TU_CONTRASEÑA
     spring.jpa.hibernate.ddl-auto=update
     ```

3. **Compila y ejecuta:**
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```

---

## 💻 Ejemplo de uso

```
--- Menú Literalura ---
1. Buscar libro por título
2. Listar libros registrados
...
Elige una opción: 1
Título a buscar: don quijote

Resultados encontrados:
Título: Don Quijote
Autor: Cervantes Saavedra, Miguel de (1547 - 1616)
Idioma(s): [es]
Descargas: 16507
Leer online: https://www.gutenberg.org/ebooks/2000.html.images
-------------------------
```

---

## 🖼️ Capturas de pantalla

![Ejemplo de menú](docs/menu-ejemplo.png)

---
## 📂 Requisitos previos
- Java 17 o superior
- Maven 3.6.0 o superior
- PostgreSQL 15 o superior  
- IDE (IntelliJ IDEA, Eclipse, etc.)
- Conexión a Internet (para acceder a la API de Gutendex)


## 📦 Estructura del proyecto

```
src/
  main/
    java/com/literatura/literalura/
      model/
      repository/
      service/
      LiteraluraApplication.java
    resources/
      application.properties
      data.sql
  test/
    java/com/literatura/literalura/service/
      BookServiceTest.java
docs/
  menu-ejemplo.png
README.md
pom.xml
```

---

## 💡 Ideas para expandir

- Exponer la API REST con Swagger/OpenAPI.
- Añadir autenticación y roles.
- Crear una interfaz web (React, Angular, Thymeleaf).
- Dockerizar la aplicación y la base de datos.
- Mejorar la internacionalización y la experiencia de usuario.

---

## 👨‍💻 Autor

Desarrollado por [ariel323](https://github.com/ariel323)

---

## 📝 Licencia

Este proyecto está bajo la licencia MIT.
