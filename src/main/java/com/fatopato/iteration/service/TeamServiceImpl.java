package com.fatopato.iteration.service;

import com.fatopato.iteration.dto.TeamDto;
import com.fatopato.iteration.entity.Organization;
import com.fatopato.iteration.entity.Team;
import com.fatopato.iteration.entity.TeamMember;
import com.fatopato.iteration.exception.BaseEntityNotFoundException;
import com.fatopato.iteration.exception.EntityAlreadyExistsException;
import com.fatopato.iteration.exception.OrganizationNotFoundException;
import com.fatopato.iteration.exception.TeamNotFoundException;
import com.fatopato.iteration.repository.TeamRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class TeamServiceImpl extends BaseService<TeamDto, Team> implements TeamService {

    @NonNull
    private final TeamRepository repository;

    @NonNull
    private final OrganizationService organizationService;

    protected TeamServiceImpl(TeamRepository repository, OrganizationService organizationService) {
        super(repository, "Team");
        this.repository = repository;
        this.organizationService = organizationService;
    }
    @Override
    public TeamDto toDto(Team entity) {
        TeamDto dto = new TeamDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setOrganizationId(entity.getOrganization() != null ? entity.getOrganization().getId() : null);
        dto.setParentId(entity.getParent() != null ? entity.getParent().getId() : null);
        dto.setTeamMemberIds(entity.getTeamMembers().stream().map(TeamMember::getId).collect(Collectors.toList()));
        return dto;
    }

    @Override
    public Team toEntity(TeamDto dto) {
        Team entity = new Team();
        entity.setId(dto.getId());
        entity.setName(dto.getName());

        if (dto.getOrganizationId() != null) {
            Organization organization = new Organization();
            organization.setId(dto.getOrganizationId());
            entity.setOrganization(organization);
        }

        if (dto.getParentId() != null) {
            Team parent = new Team();
            parent.setId(dto.getParentId());
            entity.setParent(parent);
        }
        return entity;
    }

    @Override
    public void validate(TeamDto dto, boolean isUpdate) {

        boolean organizationExists = organizationService.isExists(dto.getOrganizationId());
        if (!organizationExists) throw new OrganizationNotFoundException(dto.getOrganizationId());

        if (dto.getParentId() != null) {
            boolean parentExists = repository.existsById(dto.getParentId());
            if (!parentExists) throw new BaseEntityNotFoundException("Invalid parent team id: " + dto.getParentId());
        }
        boolean existsByName = repository.existsByName(dto.getName());
        if (existsByName) throw new EntityAlreadyExistsException("Team already exists with name: " + dto.getName());
    }

}
