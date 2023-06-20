package com.example.goaltrackingspringboot.controller;

import com.example.goaltrackingspringboot.dto.GoalDto;
import com.example.goaltrackingspringboot.model.Goal;
import com.example.goaltrackingspringboot.model.Response;
import com.example.goaltrackingspringboot.service.GoalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@Slf4j
@RequiredArgsConstructor
public class GoalController {
    private final GoalService goalService;

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

    @DeleteMapping("/goal/{id}")
    public Response deleteGoalById(@PathVariable Long id) {
        return goalService.deleteGoalById(id);
    }

    @PutMapping("/goal")
    public Response updateGoal(@RequestBody Goal goal) {
        return goalService.updateGoal(goal);
    }
}
