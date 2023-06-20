package com.example.goaltrackingspringboot.dto;

import com.example.goaltrackingspringboot.model.OkrGoal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoalDto {
    private Long createdBy;
    private String name;
    private String finishBy;
    private int difficulty;
    private String type;
    private List<OkrGoal> okrGoals = new ArrayList<>();
}
