package com.example.libraryjava.service;

import com.example.libraryjava.model.User;
import org.springframework.stereotype.Service;
import com.example.libraryjava.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements Iuser {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(long id, User user) {
        if (user != null) {
            User newUser = userRepository.getReferenceById(id);
            newUser.setName(user.getName());
            newUser.setPassword(user.getPassword());
        }
    }

    @Override
    public User getOneUser(long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }
}