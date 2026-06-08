# Spring Boot Library API

A REST API for managing a library built with Java Spring Boot and H2 in-memory database. Supports full CRUD operations with automatic Swagger UI documentation.

## Features

- Full CRUD operations for books
- Search by title, author, and genre
- Filter available books
- H2 in-memory database with JPA/Hibernate
- Auto-generated Swagger UI documentation
- Clean layered architecture (Controller, Service, Repository)

## Tech Stack

![Java](https://img.shields.io/badge/Java-21-orange?logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.14-green?logo=springboot)
![H2](https://img.shields.io/badge/Database-H2-blue)
![Maven](https://img.shields.io/badge/Build-Maven-red?logo=apachemaven)

## Installation

```bash
git clone https://github.com/mariolkotsiai/java-springboot-library.git
cd java-springboot-library
mvn spring-boot:run
```

Open your browser at `http://localhost:8080/swagger-ui/index.html`

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/books | Get all books |
| GET | /api/books/{id} | Get book by ID |
| GET | /api/books/search?title= | Search by title |
| GET | /api/books/author/{author} | Get by author |
| GET | /api/books/genre/{genre} | Get by genre |
| GET | /api/books/available | Get available books |
| POST | /api/books | Create new book |
| PUT | /api/books/{id} | Update book |
| DELETE | /api/books/{id} | Delete book |

## Project Structure

src/main/java/com/matiol/library/
├── controller/
│   └── BookController.java    # REST endpoints
├── service/
│   └── BookService.java       # Business logic
├── repository/
│   └── BookRepository.java    # Data access layer
├── model/
│   └── Book.java              # Entity
└── LibraryApplication.java    # Entry point

## License

MIT License