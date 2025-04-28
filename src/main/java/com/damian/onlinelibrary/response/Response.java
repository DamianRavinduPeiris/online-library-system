package com.damian.onlinelibrary.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class Response implements Serializable {
    private HttpStatus status;
    private String message;
    private Object data;
}
