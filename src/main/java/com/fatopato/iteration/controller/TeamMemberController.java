package com.fatopato.iteration.controller;

import com.fatopato.iteration.dto.TeamMemberDto;
import com.fatopato.iteration.service.OrganizationService;
import com.fatopato.iteration.service.TeamMemberService;
import lombok.NonNull;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teamMembers")
public class TeamMemberController extends BaseController<TeamMemberDto>{

    private final TeamMemberService service;

    public TeamMemberController(TeamMemberService service) {
        super(service);
        this.service = service;
    }
}
