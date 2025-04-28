package com.damian.onlinelibrary.service.impl;

import com.damian.onlinelibrary.dto.BookDTO;
import com.damian.onlinelibrary.entity.Book;
import com.damian.onlinelibrary.repo.BookRepo;
import com.damian.onlinelibrary.response.Response;
import com.damian.onlinelibrary.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class BookServiceImpl implements BookService {
    private final ModelMapper modelMapper;
    private final BookRepo bookRepo;

    @Override
    public ResponseEntity<Response> registerBook(BookDTO bookDTO) {
        var book = searchBookByIdBook(bookDTO.getId());
        if (book.getStatusCode() == HttpStatus.OK) {
            return buildAndSendResponse(HttpStatus.BAD_REQUEST, "Book already exists!", null);
        } else {
            var bookEntity = modelMapper.map(bookDTO, Book.class);
            bookRepo.save(bookEntity);
            return buildAndSendResponse(HttpStatus.CREATED, "Book registered successfully", bookDTO);
        }
    }

    @Override
    public ResponseEntity<Response> searchBookByIdBook(String bookId) {
        var book = bookRepo.findById(bookId);
        if (book.isPresent()) {
            var bookDTO = modelMapper.map(book.get(), BookDTO.class);
            return buildAndSendResponse(HttpStatus.OK, "Book found", bookDTO);
        } else {
            return buildAndSendResponse(HttpStatus.NOT_FOUND, "Book not found", null);
        }
    }

    @Override
    /*This method returns all the books available for borrowing.*/
    public ResponseEntity<Response> getAvailableBooks() {
        var books = bookRepo.findAll();
        if (books.isEmpty()) {
            return buildAndSendResponse(HttpStatus.NOT_FOUND, "No books available!", null);
        } else {
            var bookDTOs = books.stream()
                    .filter(book -> book.getAvailableCopies() > 0)
                    .map(book -> modelMapper.map(book, BookDTO.class))
                    .toList();
            return buildAndSendResponse(HttpStatus.OK, "Available books!", bookDTOs);
        }
    }

    /*This method returns the books by author.*/
    @Override
    public ResponseEntity<Response> getBooksByAuthor(String authorName) {
        var book = bookRepo.findByAuthor(authorName);
        if (book.isEmpty()) {
            return buildAndSendResponse(HttpStatus.NOT_FOUND, "No books found by this author!", null);
        } else {
            var bookDTOs = book.stream()
                    .map(b -> modelMapper.map(b, BookDTO.class))
                    .toList();
            return buildAndSendResponse(HttpStatus.OK, "Books found by this author!", bookDTOs);
        }
    }

    /*This method returns the books by published year.*/
    @Override
    public ResponseEntity<Response> getBooksByPublishedYear(String publishedYear) {
        var book = bookRepo.findByPublishedYear(publishedYear);
        if (book.isEmpty()) {
            return buildAndSendResponse(HttpStatus.NOT_FOUND, "No books found published in this year!", null);
        } else {
            var bookDTOs = book.stream()
                    .map(b -> modelMapper.map(b, BookDTO.class))
                    .toList();
            return buildAndSendResponse(HttpStatus.OK, "Books found published in this year!", bookDTOs);
        }
    }

    public ResponseEntity<Response> buildAndSendResponse(HttpStatus status, String message, Object data) {
        var response = new Response(status, message, data);
        return new ResponseEntity<>(response, status);
    }
}
