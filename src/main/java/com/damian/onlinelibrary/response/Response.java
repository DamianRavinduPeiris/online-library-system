package com.damian.onlinelibrary.response;

import lombok.AllArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
public class Response implements Serializable {
    private String message;
    private Object data;
}
