package com.example.goaltrackingspringboot.repository;

import com.example.goaltrackingspringboot.model.JwtToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JwtTokenRepository extends JpaRepository<JwtToken, Long> {
    @Query(value = "select jwt_token.id, jwt_token.expired, jwt_token.revoked, jwt_token.token, jwt_token.user_id from jwt_token inner join User on jwt_token.user_id = User.id where User.id = :id and (jwt_token.expired = false and jwt_token.revoked = false)", nativeQuery = true)
    List<JwtToken> findAllValidTokenByUser(Long id);

    Optional<JwtToken> findByToken(String token);
}
