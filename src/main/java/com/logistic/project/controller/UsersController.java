package com.logistic.project.controller;

import com.logistic.project.service.Impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.OutputStream;

@RestController
@RequestMapping("/api")
public class UsersController {
    @Autowired
    private UserService service;

    @GetMapping(value = "/userstream")
    public ResponseEntity<StreamingResponseBody> fetchUsersStream() {

        StreamingResponseBody stream = this::writeTo;

        return new ResponseEntity<>(stream, HttpStatus.OK);
    }

    private void writeTo(OutputStream outputStream) {
        service.writeToOutputStream(outputStream);
    }
}