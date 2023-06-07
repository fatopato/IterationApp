package com.fatopato.iteration.controller;

import com.fatopato.iteration.dto.ExternalSquadDto;
import com.fatopato.iteration.dto.OrganizationDto;
import com.fatopato.iteration.exception.EntityAlreadyExistsException;
import com.fatopato.iteration.exception.OrganizationNotFoundException;
import com.fatopato.iteration.service.ExternalSquadService;
import com.fatopato.iteration.service.OrganizationService;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/externalSquad")
public class ExternalSquadController extends BaseController<ExternalSquadDto>{

    private final ExternalSquadService service;

    public ExternalSquadController(ExternalSquadService service) {
        super(service);
        this.service = service;
    }
}
