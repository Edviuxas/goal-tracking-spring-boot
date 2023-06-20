package com.example.goaltrackingspringboot.repository;

import com.example.goaltrackingspringboot.GoalTrackingSpringBootApplication;
import com.example.goaltrackingspringboot.model.Goal;
import com.example.goaltrackingspringboot.model.OkrGoal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.repository.Repository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
public class GoalRepoTests {
    @Autowired
    private GoalRepository goalRepository;

    @Test
    public void saveGoal_ShouldReturnGoal() {
        Goal goal = Goal.builder().name("llaa").type("Personal goal").createdBy(552L).finishBy("2023-09-09").difficulty(5).okrGoals(new ArrayList<>()).build();
        Goal savedGoal = goalRepository.save(goal);
        assertThat(goalRepository.findById(savedGoal.getId()).get()).isEqualTo(savedGoal);
    }

    @Test
    public void deleteGoal_ShouldReturnEmptyOptional() {
        Goal goal = Goal.builder().name("llaa").type("Personal goal").createdBy(552L).finishBy("2023-09-09").difficulty(5).okrGoals(new ArrayList<>()).build();
        Goal savedGoal = goalRepository.save(goal);
        goalRepository.deleteById(savedGoal.getId());
        assertThat(goalRepository.findById(savedGoal.getId())).isEmpty();
    }

    @Test
    public void getAllGoals_ShouldReturn2Goals() {
        Goal goal1 = Goal.builder().name("llaa").type("Personal goal").createdBy(552L).finishBy("2023-09-09").difficulty(5).okrGoals(new ArrayList<>()).build();
        Goal goal2 = Goal.builder().name("llaaaaa").type("Personal goal").createdBy(552L).finishBy("2023-09-11").difficulty(1).okrGoals(new ArrayList<>()).build();
        goalRepository.save(goal1);
        goalRepository.save(goal2);
        assertThat(goalRepository.findAll()).hasSize(2);
    }
}
