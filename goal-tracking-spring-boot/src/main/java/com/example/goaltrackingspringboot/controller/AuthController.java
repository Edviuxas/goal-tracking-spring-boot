package com.example.goaltrackingspringboot.controller;

import com.example.goaltrackingspringboot.dto.AuthRequestDto;
import com.example.goaltrackingspringboot.dto.RefreshTokenDto;
import com.example.goaltrackingspringboot.dto.UserDto;
import com.example.goaltrackingspringboot.model.Response;
import com.example.goaltrackingspringboot.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public Response register(@RequestBody UserDto userDto) {
        return authService.register(userDto);
    }

    @PostMapping("/login")
    public Response login(@RequestBody AuthRequestDto request) {
        return authService.authenticate(request);
    }

    @PostMapping("/refresh-token")
    public Response refreshToken(@RequestBody RefreshTokenDto refreshTokenDto) {
        String refreshToken = refreshTokenDto.getRefreshToken();
        return authService.refreshToken(refreshToken);
    }
}
