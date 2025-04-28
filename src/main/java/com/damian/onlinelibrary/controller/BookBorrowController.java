package com.damian.onlinelibrary.controller;

import com.damian.onlinelibrary.response.Response;
import com.damian.onlinelibrary.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/library", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class BookBorrowController {
    private final BookService bookService;

    @PostMapping(path = "/borrow/{userId}/{bookId}")
    public ResponseEntity<Response> borrowBook(@PathVariable("userId") String userId, @PathVariable("bookId") String bookId) {
        return bookService.borrowBook(userId, bookId);

    }

    @PostMapping(path = "/return/{userId}/{bookId}")
    public ResponseEntity<Response> returnBook(@PathVariable("userId") String userId, @PathVariable("bookId") String bookId) {
        return bookService.returnBook(userId, bookId);

    }

    @GetMapping(path = "/borrow/history/{userId}")
    public ResponseEntity<Response> getBorrowHistory(@PathVariable("userId") String userId) {
        return bookService.getBorrowRecordHistory(userId);
    }

}
