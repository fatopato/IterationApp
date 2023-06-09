package com.fatopato.iteration.controller;

import com.fatopato.iteration.dto.OrganizationDto;
import com.fatopato.iteration.service.OrganizationService;
import lombok.NonNull;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/organizations")
public class OrganizationController extends BaseController<OrganizationDto>{
    @NonNull
    private final OrganizationService service;

    public OrganizationController(OrganizationService service) {
        super(service);
        this.service = service;
    }
}
