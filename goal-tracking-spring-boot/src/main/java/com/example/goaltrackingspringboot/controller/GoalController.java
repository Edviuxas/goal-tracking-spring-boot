package com.example.goaltrackingspringboot.controller;

import com.example.goaltrackingspringboot.model.Goal;
import com.example.goaltrackingspringboot.model.Response;
import com.example.goaltrackingspringboot.service.GoalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class GoalController {

    @Autowired
    GoalService goalService;

    @PostMapping("/goal")
    public Goal createGoal(@RequestBody Goal goal) {
        return goalService.createGoal(goal);
    }

    @GetMapping("/goals")
    public List<Goal> getAllGoals() {
        return goalService.getAllGoals();
    }

    @GetMapping("/goal/{id}")
    public Response getGoalById(@PathVariable Long id) {
        return goalService.getGoalById(id);
    }
}
