package com.example.goaltrackingspringboot.service;

import com.example.goaltrackingspringboot.model.Goal;
import com.example.goaltrackingspringboot.model.Response;
import com.example.goaltrackingspringboot.repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GoalService {
    @Autowired
    GoalRepository goalRepository;

    public Goal createGoal(Goal goal) {
        return goalRepository.save(goal);
    }

    public List<Goal> getAllGoals() {
        return goalRepository.findAll();
    }

    public Response getGoalById(Long id) {
        Optional<Goal> returnedGoalFromRepo = goalRepository.findById(id);
        return returnedGoalFromRepo.map(goal -> Response.builder().status(200).data(goal).message("request successful").build())
                .orElseGet(() -> Response.builder().status(422).data(null).message("goal with this id not found").build());
    }
}
