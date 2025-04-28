package com.damian.onlinelibrary.controller;

import com.damian.onlinelibrary.dto.UserDTO;
import com.damian.onlinelibrary.response.Response;
import com.damian.onlinelibrary.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping(path = "/register")
    public ResponseEntity<Response> registerUser(@RequestBody UserDTO userDTO) {
        return userService.registerUser(userDTO);
    }

    @GetMapping(path = "/searchUser/{userId}")
    public ResponseEntity<Response> searchUser(@PathVariable String userId) {
        return userService.searchUserById(userId);
    }
}
