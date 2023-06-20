package com.example.goaltrackingspringboot.service;

import com.example.goaltrackingspringboot.dto.GoalDto;
import com.example.goaltrackingspringboot.model.Goal;
import com.example.goaltrackingspringboot.model.Response;
import com.example.goaltrackingspringboot.repository.GoalRepository;
//import com.example.goaltrackingspringboot.repository.OkrGoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GoalService {
    private final GoalRepository goalRepository;
//    private final OkrGoalService okrGoalService;

    public Goal createGoal(Goal goal) {
//        System.out.println(goalDto);
//        Goal goalToSave = Goal.builder()
//                .name(goalDto.getName())
//                .createdBy(goalDto.getCreatedBy())
//                .difficulty(goalDto.getDifficulty())
//                .finishBy(goalDto.getFinishBy())
//                .type(goalDto.getType())
//                .build();
//        okrGoalRepository.saveAll(goalDto.getOkrGoals())
        Goal savedGoal = goalRepository.save(goal);
//        okrGoalService.saveOkrGoals(goalDto.getOkrGoals(), savedGoal);
        return savedGoal;
    }

    public List<Goal> getAllGoals() {
        return goalRepository.findAll();
    }

    public Response getGoalById(Long id) {
        Optional<Goal> returnedGoalFromRepo = goalRepository.findById(id);
        return returnedGoalFromRepo.map(goal -> Response.builder().status(200).data(goal).message("request successful").build())
                .orElseGet(() -> Response.builder().status(422).data(null).message("goal with this id not found").build());
    }

    public Response deleteGoalById(Long id) {
        goalRepository.deleteById(id);
        return Response.builder().status(200).data(null).message("Deleted successfully").build();
    }

    public Response updateGoal(Goal goal) {
        return Response.builder().status(200).data(goalRepository.save(goal)).message("Updated successfully").build();
    }
}
