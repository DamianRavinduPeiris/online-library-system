package com.damian.onlinelibrary.service;

import com.damian.onlinelibrary.dto.BookDTO;
import com.damian.onlinelibrary.response.Response;
import org.springframework.http.ResponseEntity;

public interface BookService {
    ResponseEntity<Response> registerBook(BookDTO bookDTO);

    ResponseEntity<Response> searchBookByIdBook(String bookId);

    ResponseEntity<Response> getAvailableBooks();

    ResponseEntity<Response> getBooksByAuthor(String authorName);

    ResponseEntity<Response> getBooksByPublishedYear(String publishedYear);

    ResponseEntity<Response> getAllBooks();

    ResponseEntity<Response> borrowBook(String userId, String bookId);

    ResponseEntity<Response> returnBook(String userId, String bookId);

    ResponseEntity<Response> getBorrowRecordHistory(String userId);


}
