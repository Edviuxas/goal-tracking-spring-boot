package com.example.goaltrackingspringboot.service;

import com.example.goaltrackingspringboot.model.Goal;
import com.example.goaltrackingspringboot.model.Response;
import com.example.goaltrackingspringboot.repository.GoalRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

//@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class GoalServiceTests {
    @Mock
    private GoalRepository goalRepository;

    @InjectMocks
    private GoalService goalService;

//    @Before
//    public void setUp(){
//        MockitoAnnotations.initMocks(this);
//    }

    @Test
    public void saveGoal_ShouldReturnGoal() {
        //Arrange
        Goal goalToSave = Goal.builder().name("llaa").type("Personal goal").createdBy(552L).finishBy("2023-09-09").difficulty(5).okrGoals(new ArrayList<>()).build();
        when(goalRepository.save(any(Goal.class))).thenReturn(goalToSave);

        //Act
        Goal savedGoal = goalService.createGoal(new Goal());

        //Assert
        assertThat(savedGoal).isNotNull();
        verify(goalRepository, times(1)).save(any(Goal.class));
        verifyNoMoreInteractions(goalRepository);
    }

    @Test
    public void getAllGoals_ShouldReturnListOfGoals() {
        List<Goal> goalsToSave = new ArrayList<>();
        Goal goal1 = Goal.builder().name("llaa").type("Personal goal").createdBy(552L).finishBy("2023-09-09").difficulty(5).okrGoals(new ArrayList<>()).build();
        Goal goal2 = Goal.builder().name("llaaaaa").type("Team goal").createdBy(552L).finishBy("2023-09-11").difficulty(1).okrGoals(new ArrayList<>()).build();
        goalsToSave.add(goal1);
        goalsToSave.add(goal2);
        when(goalRepository.findAll()).thenReturn(goalsToSave);

        List<Goal> allGoalsFound = goalService.getAllGoals();

        assertThat(allGoalsFound).hasSize(2);
    }

    @Test
    public void getGoalById_ShouldReturnGoal() {
        Goal goalToSave = Goal.builder().name("llaa").type("Personal goal").createdBy(552L).finishBy("2023-09-09").difficulty(5).okrGoals(new ArrayList<>()).build();
        when(goalRepository.findById(anyLong())).thenReturn(Optional.of(goalToSave));

        Response receivedResponse = goalService.getGoalById(getRandomLong());

        assertThat(receivedResponse.getStatus()).isEqualTo(200);
        assertThat(receivedResponse.getData()).isEqualTo(goalToSave);
    }

    @Test
    public void deleteGoalById_ShouldThrowException() {
        goalService.deleteGoalById(11L);

        verify(goalRepository, times(1)).deleteById(11L);
    }

    @Test
    public void updateGoal_ShouldReturnUpdatedGoal() {
        Goal goalToSave = Goal.builder().id(11L).name("llaa").type("Personal goal").createdBy(552L).finishBy("2023-09-09").difficulty(5).okrGoals(new ArrayList<>()).build();
        when(goalRepository.save(any(Goal.class))).thenReturn(goalToSave);
        goalToSave.setName("changed name");
        goalToSave.setDifficulty(1);

        Response receivedResponse = goalService.updateGoal(goalToSave);

        assertThat(((Goal) receivedResponse.getData()).getDifficulty()).isEqualTo(1);
        assertThat(((Goal) receivedResponse.getData()).getName()).isEqualTo("changed name");
        verify(goalRepository, times(1)).save(goalToSave);
    }

    private Long getRandomLong() {
        return new Random().longs(1, 10).findFirst().getAsLong();
    }
}
