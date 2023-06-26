package com.example.goaltrackingspringboot.controller;

import com.example.goaltrackingspringboot.dto.UserDto;
import com.example.goaltrackingspringboot.model.Response;
import com.example.goaltrackingspringboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:3000"})
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

    @GetMapping("/user")
    public Response getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }

    @PutMapping("/user/{id}")
    public Response updateUser(@PathVariable(name = "id") Long id, @RequestBody UserDto userDto) {
        return userService.updateUser(id, userDto);
    }
}
