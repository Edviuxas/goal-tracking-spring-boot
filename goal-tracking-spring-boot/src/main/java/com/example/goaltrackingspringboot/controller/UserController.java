package com.example.goaltrackingspringboot.controller;

import com.example.goaltrackingspringboot.dto.UserDto;
import com.example.goaltrackingspringboot.model.User;
import com.example.goaltrackingspringboot.service.GoalService;
import com.example.goaltrackingspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user")
    public User createUser(@RequestBody UserDto user) {
        return userService.createUser(user);
    }

}
