package com.example.goaltrackingspringboot.service;

import com.example.goaltrackingspringboot.model.Response;
import com.example.goaltrackingspringboot.model.Team;
import com.example.goaltrackingspringboot.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    public Response createTeam(Team team) {
//        System.out.println(team);
        Team savedTeam = teamRepository.save(team);
        return Response.builder().status(201).data(savedTeam).message("Team created successfully").build();
    }
}
