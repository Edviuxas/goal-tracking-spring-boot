package com.example.goaltrackingspringboot.service;

import com.example.goaltrackingspringboot.model.Goal;
import com.example.goaltrackingspringboot.model.Response;
import com.example.goaltrackingspringboot.model.User;
import com.example.goaltrackingspringboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Response getAllUsers() {
        return Response.builder().status(200).data(userRepository.findAll()).message("Retrieved successfully").build();
    }

    public Response getUserById(Long id) {
        Optional<User> returnedUserFromRepo = userRepository.findById(id);
        return returnedUserFromRepo.map(user -> Response.builder().status(200).data(user).message("request successful").build())
                .orElseGet(() -> Response.builder().status(422).data(null).message("user with this id not found").build());
    }
}
