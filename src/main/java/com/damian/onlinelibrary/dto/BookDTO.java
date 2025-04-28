package com.damian.onlinelibrary.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BookDTO implements Serializable {
    private String id;
    private String title;
    private String author;
    private String published_year;
    private int available_copies;
}
