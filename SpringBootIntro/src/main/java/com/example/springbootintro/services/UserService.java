package com.example.springbootintro.services;

import com.example.springbootintro.models.User;

public interface UserService {
    void register(String userName , Integer age);
    User findByUserName(String username);
}
