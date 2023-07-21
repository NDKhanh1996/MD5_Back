package com.example.libraryjava.service;

import com.example.libraryjava.model.Book;

import java.util.List;

public interface IBookService {
    public void addBook(Book book);

    public void updateBook(long id, Book book);

    public void deleteBook(long id);

    public List<Book> getAllBook();

    public Book getOneBook(long id);
}