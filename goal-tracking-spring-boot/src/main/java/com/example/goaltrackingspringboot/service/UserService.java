package com.example.goaltrackingspringboot.service;

import com.example.goaltrackingspringboot.dto.AuthResponseDto;
import com.example.goaltrackingspringboot.model.Goal;
import com.example.goaltrackingspringboot.model.Response;
import com.example.goaltrackingspringboot.model.User;
import com.example.goaltrackingspringboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public Response getAllUsers() {
        return Response.builder().status(200).data(userRepository.findAll()).message("Retrieved successfully").build();
    }

    public Response getUserById(Long id) {
        Optional<User> returnedUserFromRepo = userRepository.findById(id);
        if (returnedUserFromRepo.isPresent()) {
            User returnedUser = returnedUserFromRepo.get();
            AuthResponseDto responseDto = AuthResponseDto.builder().id(returnedUser.getId()).team(returnedUser.getTeam()).email(returnedUser.getEmail()).accessToken(jwtService.generateToken(returnedUser)).refreshToken(jwtService.generateRefreshToken(returnedUser)).build();
            return Response.builder().status(200).data(responseDto).message("request successful").build();
        } else {
            return Response.builder().status(422).data(null).message("user with this id not found").build();
        }
//        return returnedUserFromRepo.map(user -> Response.builder().status(200).data(user).message("request successful").build())
//                .orElseGet(() -> Response.builder().status(422).data(null).message("user with this id not found").build());
    }

    public Response getUserByEmail(String emailAddress) {
        Optional<User> returnedUserFromRepo = userRepository.findByEmail(emailAddress);
        return returnedUserFromRepo.map(user -> Response.builder().status(200).data(user).message("request successful").build())
                .orElseGet(() -> Response.builder().status(422).data(null).message("user with this email not found").build());
    }
}
