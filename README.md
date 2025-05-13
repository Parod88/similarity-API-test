# 🛠 Product Similarity API

Este proyecto implementa una API RESTful desarrollada con **Spring Boot**, cuyo objetivo es ofrecer productos similares a uno dado, integrando con endpoints existentes simulados por mocks.

---

## 🚀 Funcionalidad

- Endpoint principal: `GET /product/{productId}/similar`
- Para un producto dado, devuelve los detalles de los productos similares.
- Llama a mocks disponibles en `localhost:3001`.

---

## 🧰 Stack tecnológico

- Java 17+
- Spring Boot 3+
- Maven
- RestTemplate (para llamadas HTTP)
- CompletableFuture y @Async (llamadas paralelas)

---

## 🗂 Estructura del proyecto

```plaintext
src/
├── main/
│   ├── java/com/example/productsimilarity/
│   │   ├── ProductSimilarityApplication.java
│   │   ├── controller/ProductController.java
│   │   ├── model/Product.java
│   │   └── service/ProductService.java
│   └── resources/
│       └── application.properties
```

---

## 🏁 ¿Cómo correr el proyecto?

### 1. Requisitos

- Java 17+
- Docker y Docker Compose
- Maven (`./mvnw` incluido en el proyecto)
- IDE recomendado: IntelliJ IDEA o VSCode

### 2. Levantar la aplicación

Desde la raíz del proyecto:

```bash
./mvnw spring-boot:run
```

La aplicación estará disponible en:

```
http://localhost:5000
```

Ejemplo:

```
curl http://localhost:5000/product/1/similar
```

---

## 🛡️ Manejo de errores

- Si falla el llamado a `/similarids`, se devuelve una lista vacía.
- Si falla algún producto individual, se omite pero el resto se incluye.
- Los errores se registran en consola para trazabilidad.

---

## ⚡ Mejora de rendimiento

- Las llamadas a `/product/{id}` se ejecutan en paralelo con `CompletableFuture`.
- Se usa `@Async` y un `Executor` para acelerar la respuesta.

---

## 📦 Build y empaquetado (opcional)

Generar un `.jar` con:

```
./mvnw clean package
```

Lanzarlo con:

```
java -jar target/product-similarity-0.0.1-SNAPSHOT.jar
```

---

## 👨‍💻 Autor

Desarrollado por Pablo Rodríguez como parte de una prueba técnica para el puesto de FullStack Developer.
