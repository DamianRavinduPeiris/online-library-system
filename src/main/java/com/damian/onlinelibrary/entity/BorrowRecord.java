package com.damian.onlinelibrary.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BorrowRecord {
    @Id
    private String borrowRecordId;

    @ManyToOne
    private User user;

    @ManyToOne
    private Book book;

    private String borrowedAt;

    private String returnedAt;
}
