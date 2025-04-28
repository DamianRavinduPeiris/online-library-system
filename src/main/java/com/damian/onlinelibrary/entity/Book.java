package com.damian.onlinelibrary.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {
    @Id
    private String id;
    private String title;
    private String author;
    private String published_year;
    private int available_copies;
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<BorrowRecord> borrowRecords;
}
