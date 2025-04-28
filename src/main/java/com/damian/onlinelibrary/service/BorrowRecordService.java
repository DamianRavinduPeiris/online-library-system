package com.damian.onlinelibrary.service;

import com.damian.onlinelibrary.entity.Book;
import com.damian.onlinelibrary.entity.User;
import com.damian.onlinelibrary.response.Response;
import org.springframework.http.ResponseEntity;

public interface BorrowRecordService {
    ResponseEntity<Response> borrowBook(User user, Book book);

    ResponseEntity<Response> returnBook(User user, Book book);

    ResponseEntity<Response> getUsersBorrowHistory(User user);
}
