package com.example.libraryjava.controller;

import com.example.libraryjava.model.Book;
import com.example.libraryjava.model.User;
import com.example.libraryjava.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllUser() {
        List<User> userList = userService.getAllUser();
        Object responseObject = new Object() {
            public final List<User> result = userList;
            public final String message = "OK";
            public final int status = HttpStatus.OK.value();
        };
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @PostMapping("addUser")
    public ResponseEntity<Object> addUser(@RequestBody User user) { // need try
        userService.addUser(user);
        Object responseObject = new Object() {
            public final String message = "User created successfully.";
            public final int status = HttpStatus.CREATED.value();
        };
        return new ResponseEntity<>(responseObject, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        userService.updateUser(id, user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable("id") long id) {
        User user = userService.getOneUser(id);
        Object responseObject = new Object() {
            public final User result = user;
            public final String message = "OK";
            public final int status = HttpStatus.OK.value();
        };
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
}