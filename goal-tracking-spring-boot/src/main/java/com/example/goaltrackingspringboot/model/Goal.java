package com.example.goaltrackingspringboot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Table(name = "goals")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long createdBy;
    private String name;
    private String finishBy;
    private int difficulty;
    private String type;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "goal_id")
    private List<OkrGoal> okrGoals = new ArrayList<>();
}
