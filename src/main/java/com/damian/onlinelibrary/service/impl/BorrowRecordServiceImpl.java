package com.damian.onlinelibrary.service.impl;

import com.damian.onlinelibrary.entity.Book;
import com.damian.onlinelibrary.entity.BorrowRecord;
import com.damian.onlinelibrary.entity.User;
import com.damian.onlinelibrary.repo.BookRepo;
import com.damian.onlinelibrary.repo.BorrowRecordRepo;
import com.damian.onlinelibrary.response.Response;
import com.damian.onlinelibrary.service.BorrowRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class BorrowRecordServiceImpl implements BorrowRecordService {
    private final BorrowRecordRepo borrowRecordRepo;
    private final BookRepo bookRepo;

    @Override
    public ResponseEntity<Response> borrowBook(User user, Book book) {
        var existingBorrowRecords = borrowRecordRepo.findByUser(user);
        if (!existingBorrowRecords.isEmpty()) {
            for (var record : existingBorrowRecords) {
                if (record.getBook().getId().equals(book.getId()) && record.getUser().getId().equals(user.getId())) {
                    return buildAndSendResponse(HttpStatus.NOT_FOUND, "Book already borrowed!", record);
                }
            }
        }
        if (book.getAvailableCopies() > 0) {
            book.setAvailableCopies(book.getAvailableCopies() - 1);
            bookRepo.save(book);
            var borrowRecord = new BorrowRecord();
            borrowRecord.setBorrowRecordId(UUID.randomUUID().toString());
            borrowRecord.setUser(user);
            borrowRecord.setBook(book);
            borrowRecord.setBorrowedAt(String.valueOf(System.currentTimeMillis()));
            borrowRecord.setReturnedAt(null);
            borrowRecordRepo.save(borrowRecord);
            return buildAndSendResponse(HttpStatus.OK, "Book borrowed successfully", borrowRecord);
        }
        return buildAndSendResponse(HttpStatus.NOT_FOUND, "Book cannot be borrowed , since there are no available copies.", book);
    }

    @Override
    public ResponseEntity<Response> returnBook(User user, Book book) {
        var borrowRecords = borrowRecordRepo.findByUser(user);
        if (!borrowRecords.isEmpty()) {
            for (var record : borrowRecords) {
                if (record.getBook().getId().equals(book.getId()) && record.getUser().getId().equals(user.getId())) {
                    if (record.getReturnedAt() == null) {
                        book.setAvailableCopies(book.getAvailableCopies() + 1);
                        bookRepo.save(book);
                        record.setBorrowedAt(null);
                        record.setReturnedAt(String.valueOf(System.currentTimeMillis()));
                        borrowRecordRepo.save(record);
                        return buildAndSendResponse(HttpStatus.OK, "Book returned successfully", record);
                    }
                    return buildAndSendResponse(HttpStatus.NOT_FOUND, "Book already returned!", record);
                }
            }
            return buildAndSendResponse(HttpStatus.NOT_FOUND, "No matching borrow record found for the given book and user.", null);
        }
        return buildAndSendResponse(HttpStatus.NOT_FOUND, "Borrowed record not found!", book);
    }

    @Override
    public ResponseEntity<Response> getUsersBorrowHistory(User user) {
        var borrowRecord = borrowRecordRepo.findByUser(user);
        if (!borrowRecord.isEmpty()) {
            var records = borrowRecord.stream().toList();
            return buildAndSendResponse(HttpStatus.OK, "Borrow records found!", records);
        }
        return buildAndSendResponse(HttpStatus.NOT_FOUND, "Borrow record not found!", null);
    }

    public ResponseEntity<Response> buildAndSendResponse(HttpStatus status, String message, Object data) {
        var response = new Response(status, message, data);
        return new ResponseEntity<>(response, status);
    }
}
