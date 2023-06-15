package com.example.goaltrackingspringboot.controller;

import com.example.goaltrackingspringboot.dto.UserDto;
import com.example.goaltrackingspringboot.model.Response;
import com.example.goaltrackingspringboot.model.User;
import com.example.goaltrackingspringboot.service.GoalService;
import com.example.goaltrackingspringboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public Response getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public Response getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}
