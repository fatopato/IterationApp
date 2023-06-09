package com.fatopato.iteration.controller;

import com.fatopato.iteration.dto.TeamDto;
import com.fatopato.iteration.service.TeamService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/teams")
public class TeamController extends BaseController<TeamDto>{


    private final TeamService service;

    public TeamController(TeamService service) {
        super(service);
        this.service = service;
    }
}
