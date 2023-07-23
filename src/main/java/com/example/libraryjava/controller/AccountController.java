package com.example.libraryjava.controller;

import com.example.libraryjava.model.Account;
import com.example.libraryjava.service.AccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/account")
@CrossOrigin
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllAccount() {
        List<Account> accountList = accountService.getAllAccount();
        Object responseObject = new Object() {
            public final List<Account> result = accountList;
            public final String message = "OK";
            public final int status = HttpStatus.OK.value();
        };
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @PostMapping("add")
    public ResponseEntity<Object> addAccount(@RequestBody Account account) { // need try
        accountService.addAccount(account);
        Object responseObject = new Object() {
            public final String message = "Account created successfully.";
            public final int status = HttpStatus.CREATED.value();
        };
        return new ResponseEntity<>(responseObject, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateAccount(@PathVariable("id") long id, @RequestBody Account account) {
        accountService.updateAccount(id, account);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Object> getOneAccount(@PathVariable("id") long id) {
        Account account = accountService.getAccountById(id);
        Object responseObject = new Object() {
            public final Account result = account;
            public final String message = "OK";
            public final int status = HttpStatus.OK.value();
        };
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Map<String, String> requestData, HttpSession session) {
        String email = requestData.get("email");
        String password = requestData.get("password");

        Account account = accountService.login(email, password);

        if (account == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials!");
        }

        Object responseObject = new Object() {
            public final Account result = account;
            public final String message = "OK";
            public final int status = HttpStatus.OK.value();
        };
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
}