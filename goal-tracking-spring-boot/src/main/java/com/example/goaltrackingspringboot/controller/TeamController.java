package com.example.goaltrackingspringboot.controller;

import com.example.goaltrackingspringboot.model.Response;
import com.example.goaltrackingspringboot.model.Team;
import com.example.goaltrackingspringboot.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

    @PostMapping("/teams")
    public Response createTeam(@RequestBody Team team) {
        System.out.println("lalalala");
        return teamService.createTeam(team);
    }
}
