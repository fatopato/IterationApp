package com.fatopato.iteration.controller;

import com.fatopato.iteration.dto.IterationDto;
import com.fatopato.iteration.dto.TeamDto;
import com.fatopato.iteration.service.IterationService;
import com.fatopato.iteration.service.TeamService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/iterations")
public class IterationController extends BaseController<IterationDto>{


    private final IterationService service;

    public IterationController(IterationService service) {
        super(service);
        this.service = service;
    }
}
