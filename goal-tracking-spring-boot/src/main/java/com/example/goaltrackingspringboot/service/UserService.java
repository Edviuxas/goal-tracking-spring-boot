package com.example.goaltrackingspringboot.service;

import com.example.goaltrackingspringboot.dto.UserDto;
import com.example.goaltrackingspringboot.model.User;
import com.example.goaltrackingspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    public User createUser(UserDto user) {
        User userToSave = new User();
        userToSave.setEmail(user.getEmail());
        userToSave.setFirstName(user.getFirstName());
        userToSave.setLastName(user.getLastName());
        userToSave.setHashedPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(userToSave);
    }
}
