package com.fatopato.iteration.controller;

import com.fatopato.iteration.dto.IterationDto;
import com.fatopato.iteration.dto.IterationItemDto;
import com.fatopato.iteration.entity.IterationItem;
import com.fatopato.iteration.service.IterationItemService;
import com.fatopato.iteration.service.IterationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/iterations")
public class IterationItemController extends BaseController<IterationItemDto>{


    private final IterationItemService service;

    public IterationItemController(IterationItemService service) {
        super(service);
        this.service = service;
    }
}
