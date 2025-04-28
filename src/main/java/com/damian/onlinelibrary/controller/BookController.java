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
}
