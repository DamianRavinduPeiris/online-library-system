package com.damian.onlinelibrary.repo;

import com.damian.onlinelibrary.entity.BorrowRecord;
import com.damian.onlinelibrary.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
public interface BorrowRecordRepo extends JpaRepository<BorrowRecord, String> {
    List<BorrowRecord> findByUser(User user);
}
