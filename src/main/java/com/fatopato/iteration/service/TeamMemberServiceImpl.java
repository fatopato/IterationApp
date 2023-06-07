package com.fatopato.iteration.service;

import com.fatopato.iteration.dto.TeamMemberDto;
import com.fatopato.iteration.dto.TeamMemberDto;
import com.fatopato.iteration.entity.ExternalSquad;
import com.fatopato.iteration.entity.Team;
import com.fatopato.iteration.entity.TeamMember;
import com.fatopato.iteration.entity.TeamMember;
import com.fatopato.iteration.exception.BaseBadRequestException;
import com.fatopato.iteration.exception.EntityAlreadyExistsException;
import com.fatopato.iteration.repository.TeamMemberRepository;
import com.fatopato.iteration.repository.TeamMemberRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class TeamMemberServiceImpl extends BaseService<TeamMemberDto, TeamMember> implements TeamMemberService {

    private final TeamMemberRepository repository;
    private final TeamService teamService;
    private final ExternalSquadService externalSquadService;

    protected TeamMemberServiceImpl(TeamMemberRepository repository, TeamService teamService, ExternalSquadService externalSquadService) {
        super(repository, "Team Member");
        this.repository = repository;
        this.teamService = teamService;
        this.externalSquadService = externalSquadService;
    }

    @Override
    public TeamMemberDto toDto(TeamMember entity) {
        TeamMemberDto dto = new TeamMemberDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setIsOutsource(entity.isOutsource());
        dto.setTeamId(entity.getTeam().getId());
        if (entity.getExternalSquad()!= null)
            dto.setExternalSquadId(entity.getExternalSquad().getId());
        return dto;
    }

    @Override
    public TeamMember toEntity(TeamMemberDto dto) {
        TeamMember entity = new TeamMember();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setOutsource(dto.getIsOutsource());
        entity.setTeam(new Team(dto.getTeamId()));
        if (dto.getExternalSquadId() != null)
            entity.setExternalSquad(new ExternalSquad(dto.getExternalSquadId()));
        return entity;
    }

    @Override
    public void validate(TeamMemberDto dto, boolean isUpdate) {
        boolean existsByName = repository.existsByName(dto.getName());
        if (existsByName) throw new EntityAlreadyExistsException("Team Member already exists with name: " + dto.getName());
        boolean teamExists = teamService.isExists(dto.getTeamId());
        if (!teamExists) throw new BaseBadRequestException("Invalid team id: " + dto.getTeamId());
        if (dto.getExternalSquadId() != null) {
            boolean squadServiceExists = externalSquadService.isExists(dto.getExternalSquadId());
            if (!squadServiceExists) throw new BaseBadRequestException("Invalid external squad id: " + dto.getExternalSquadId());
        }
    }
}
