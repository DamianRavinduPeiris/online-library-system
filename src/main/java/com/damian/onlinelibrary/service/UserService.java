package com.damian.onlinelibrary.service;

import com.damian.onlinelibrary.dto.UserDTO;
import com.damian.onlinelibrary.response.Response;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<Response> registerUser(UserDTO userDTO);

}
