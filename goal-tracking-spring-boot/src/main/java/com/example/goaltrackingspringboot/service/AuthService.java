package com.example.goaltrackingspringboot.service;

import com.example.goaltrackingspringboot.dto.AuthRequestDto;
import com.example.goaltrackingspringboot.dto.AuthResponseDto;
import com.example.goaltrackingspringboot.dto.UserDto;
import com.example.goaltrackingspringboot.model.Response;
import com.example.goaltrackingspringboot.model.JwtToken;
import com.example.goaltrackingspringboot.model.User;
import com.example.goaltrackingspringboot.repository.JwtTokenRepository;
import com.example.goaltrackingspringboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtTokenRepository jwtTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public Response register(UserDto userDto) {
        User user = User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .hashedPassword(passwordEncoder.encode(userDto.getPassword()))
//                .role(Role.USER)
                .build();
        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
//        return AuthResponseDto.builder()
//                .accessToken(jwtToken)
//                .refreshToken(refreshToken)
//                .build();
        AuthResponseDto authResponseDto = AuthResponseDto.builder().accessToken(jwtToken).refreshToken(refreshToken)
                .build();
        return Response.builder()
                .status(201)
                .data(authResponseDto)
                .message("Registered successfully").
                build();
    }

    public Response authenticate(AuthRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        AuthResponseDto authResponseDto = AuthResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
        return Response.builder()
                .status(200)
                .data(authResponseDto)
                .message("Login successful").
                build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = JwtToken.builder()
                .user(user)
                .token(jwtToken)
                .expired(false)
                .revoked(false)
                .build();
        jwtTokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = jwtTokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        jwtTokenRepository.saveAll(validUserTokens);
    }

    public Response refreshToken(String refreshToken) {
        final String userEmail;
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail == null) {
            return Response.builder().status(404).data(null).message("Email in token is missing").build();
        }

        var user = this.userRepository.findByEmail(userEmail)
                .orElseThrow();

        if (!jwtService.isTokenValid(refreshToken, user)) {
            Response.builder().status(400).data(null).message("Refresh token invalid").build();
        }

        var accessToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);
        var authResponse = AuthResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        return Response.builder().status(200).data(authResponse).message("Refreshed successfully").build();
    }
}
