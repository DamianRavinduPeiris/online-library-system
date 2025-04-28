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
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String created_at;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<BorrowRecord> borrowRecords;
}
