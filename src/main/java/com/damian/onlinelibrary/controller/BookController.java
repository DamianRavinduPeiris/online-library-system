package com.damian.onlinelibrary.controller;

import com.damian.onlinelibrary.dto.BookDTO;
import com.damian.onlinelibrary.response.Response;
import com.damian.onlinelibrary.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/books", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping(path = "/register")
    public ResponseEntity<Response> registerBook(@RequestBody BookDTO bookDTO) {
        return bookService.registerBook(bookDTO);
    }

    @GetMapping(path = "/searchBook/{bookId}")
    public ResponseEntity<Response> searchBook(@PathVariable("bookId") String bookId) {
        return bookService.searchBookByIdBook(bookId);
    }

    @GetMapping(path = "/getAvailableBooks")
    public ResponseEntity<Response> searchAvailableBooks() {
        return bookService.getAvailableBooks();
    }

    @GetMapping(path = "/searchBook/author/{authorName}")
    public ResponseEntity<Response> searchBookByAuthorsName(@PathVariable("authorName") String authorName) {
        return bookService.getBooksByAuthor(authorName);
    }

    @GetMapping(path = "/searchBook/year/{publishedYear}")
    public ResponseEntity<Response> searchBookByPublishedYear(@PathVariable("publishedYear") String publishedYear) {
        return bookService.getBooksByPublishedYear(publishedYear);
    }
}
