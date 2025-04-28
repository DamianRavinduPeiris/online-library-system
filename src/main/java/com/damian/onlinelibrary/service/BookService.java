package com.damian.onlinelibrary.service;

import com.damian.onlinelibrary.dto.BookDTO;
import com.damian.onlinelibrary.response.Response;
import org.springframework.http.ResponseEntity;

public interface BookService {
    ResponseEntity<Response> registerBook(BookDTO bookDTO);
    ResponseEntity<Response> getAvailableBooks();
    ResponseEntity<Response> getBooksByAuthor(String authorName);
    ResponseEntity<Response> getBooksByPublishedYear(String publishedYear);
}
