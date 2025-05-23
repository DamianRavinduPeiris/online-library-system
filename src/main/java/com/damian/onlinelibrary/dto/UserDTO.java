package com.damian.onlinelibrary.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserDTO implements Serializable {
    private String id;
    private String name;
    private String email;
    private String createdAt;
}
