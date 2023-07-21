package com.example.libraryjava.controller;

import com.example.libraryjava.model.Book;
import com.example.libraryjava.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.libraryjava.service.IBookService;

import java.util.List;

@RestController
@RequestMapping("/")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllBook() {
        List<Book> bookList = bookService.getAllBook();

        Object responseObject = new Object() {
            public final List<Book> result = bookList;
            public final String message = "OK";
            public final int status = HttpStatus.OK.value();
        };
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @PostMapping("/addBook")
    public ResponseEntity<Object> addBook(@RequestBody Book book) {
        if (book == null) {
            return new ResponseEntity<>("Book information is missing.", HttpStatus.BAD_REQUEST);
        }
        bookService.addBook(book);
        Object responseObject = new Object() {
            public final String message = "Book added successfully.";
            public final int status = HttpStatus.CREATED.value();
        };
        return new ResponseEntity<>(responseObject, HttpStatus.CREATED);
    }

}