package com.damian.onlinelibrary.service.impl;

import com.damian.onlinelibrary.dto.UserDTO;
import com.damian.onlinelibrary.entity.User;
import com.damian.onlinelibrary.repo.UserRepo;
import com.damian.onlinelibrary.response.Response;
import com.damian.onlinelibrary.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<Response> registerUser(UserDTO userDTO) {
        var user = searchUserById(userDTO.getId());
        if (user.getStatusCode() == HttpStatus.NOT_FOUND) {
            var userEntity = modelMapper.map(userDTO, User.class);
            userRepo.save(userEntity);
            log.info("User registered successfully: {}", userDTO);
            return buildAndSendResponse(HttpStatus.CREATED, "User registered successfully!", userDTO);
        } else {
            log.warn("User already registered: {}", userDTO);
            return buildAndSendResponse(HttpStatus.CONFLICT, "User already exists!", null);
        }

    }

    @Override
    public ResponseEntity<Response> searchUserById(String userId) {
        var user = userRepo.findById(userId);
        if (user.isPresent()) {
            var userDTO = modelMapper.map(user.get(), UserDTO.class);
            return buildAndSendResponse(HttpStatus.OK, "User found!", userDTO);
        } else {
            return buildAndSendResponse(HttpStatus.NOT_FOUND, "User not found!", null);
        }
    }

    public ResponseEntity<Response> buildAndSendResponse(HttpStatus status, String message, Object data) {
        var response = new Response(status, message, data);
        return new ResponseEntity<>(response, status);
    }
}
