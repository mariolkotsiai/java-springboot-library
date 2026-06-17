package com.matiol.library;

import com.matiol.library.model.Book;
import com.matiol.library.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
    }

    @Test
    void testGetAllBooks() throws Exception {
        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testCreateBook() throws Exception {
        Book book = new Book(null, "Clean Code", "Robert C. Martin", "978-0132350884", 2008, "Programming", true);

        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Clean Code"))
                .andExpect(jsonPath("$.author").value("Robert C. Martin"));
    }

    @Test
    void testGetBookById() throws Exception {
        Book book = bookRepository.save(new Book(null, "Clean Code", "Robert C. Martin", "978-0132350884", 2008, "Programming", true));

        mockMvc.perform(get("/api/books/" + book.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Clean Code"));
    }

    @Test
    void testGetBookNotFound() throws Exception {
        mockMvc.perform(get("/api/books/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdateBook() throws Exception {
        Book book = bookRepository.save(new Book(null, "Clean Code", "Robert C. Martin", "978-0132350884", 2008, "Programming", true));
        book.setAvailable(false);

        mockMvc.perform(put("/api/books/" + book.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.available").value(false));
    }

    @Test
    void testDeleteBook() throws Exception {
        Book book = bookRepository.save(new Book(null, "Clean Code", "Robert C. Martin", "978-0132350884", 2008, "Programming", true));

        mockMvc.perform(delete("/api/books/" + book.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    void testSearchByTitle() throws Exception {
        bookRepository.save(new Book(null, "Clean Code", "Robert C. Martin", "978-0132350884", 2008, "Programming", true));

        mockMvc.perform(get("/api/books/search?title=Clean"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Clean Code"));
    }
}