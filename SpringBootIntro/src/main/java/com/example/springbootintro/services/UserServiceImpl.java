package com.example.springbootintro.services;

import com.example.springbootintro.models.Account;
import com.example.springbootintro.models.User;
import com.example.springbootintro.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(String userName, Integer age) {
        if (userName.isBlank() || age < 18){
            throw new RuntimeException("Validation failed");
        }

        User user = this.userRepository.findByUserName(userName);

        if (user != null){
            throw new RuntimeException("Username already in use");
        }

        Account account = new Account();
        User newUser = new User(userName,age,account);

        this.userRepository.save(newUser);
    }

    @Override
    public User findByUserName(String username) {
        return this.userRepository.findByUserName(username);
    }
}
