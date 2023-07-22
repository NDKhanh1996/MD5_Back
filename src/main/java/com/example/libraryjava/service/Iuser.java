package com.example.libraryjava.service;

import com.example.libraryjava.model.User;

import java.util.List;

public interface Iuser {
    public List<User> getAllUser();

    public void addUser(User user);

    public void updateUser(long id, User user);

    public User getOneUser(long id);
}