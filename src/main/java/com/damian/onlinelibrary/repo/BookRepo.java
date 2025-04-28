package com.damian.onlinelibrary.repo;

import com.damian.onlinelibrary.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@EnableJpaRepositories
public interface BookRepo extends JpaRepository<Book,String> {
    /*Fetches Books by Author.*/
    Optional<Book> findByAuthor(String author);
    /*Fetches Books by published year.*/
    Optional<Book> findByPublishedYear(String publishedYear);
}
