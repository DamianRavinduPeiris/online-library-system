package com.damian.onlinelibrary.repo;

import com.damian.onlinelibrary.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface BookRepo extends JpaRepository<Book,String> {
}
