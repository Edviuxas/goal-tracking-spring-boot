package com.example.goaltrackingspringboot.repository;

import com.example.goaltrackingspringboot.model.Goal;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
}
