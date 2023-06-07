package com.fatopato.iteration.service;

import com.fatopato.iteration.dto.OrganizationDto;
import com.fatopato.iteration.dto.TeamDto;
import com.fatopato.iteration.entity.Organization;
import com.fatopato.iteration.entity.Team;
import com.fatopato.iteration.entity.TeamMember;
import com.fatopato.iteration.exception.EntityAlreadyExistsException;
import com.fatopato.iteration.exception.OrganizationNotFoundException;
import com.fatopato.iteration.exception.TeamNotFoundException;
import com.fatopato.iteration.repository.TeamRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TeamServiceImpl implements TeamService {

    @NonNull
    private final TeamRepository repository;

    @NonNull
    private final OrganizationService organizationService;

    @Override
    public TeamDto save(TeamDto dto) {
        validate(dto, false);
        Team savedTeam = repository.save(toEntity(dto));
        return toDto(savedTeam);
    }

    @Override
    public TeamDto getById(Long id) {
        return repository.findById(id).map(this::toDto).orElseThrow(() -> new TeamNotFoundException("Team not found with the id: " + id));
    }

    @Override
    public TeamDto update(TeamDto dto) {
        repository.findById(dto.getId()).orElseThrow(() -> new TeamNotFoundException("Team not found with the id: " + dto.getId()));
        return toDto(repository.save(toEntity(dto)));
    }

    @Override
    public void deleteById(Long id) {
        Team team = repository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException("Team not found with the id: " + id));
        repository.deleteById(team.getId());
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
    public List<TeamDto> getAll() {
        return repository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public void validate(TeamDto dto, boolean isUpdate) {

        boolean organizationExists = organizationService.isExists(dto.getOrganizationId());
        if (!organizationExists) throw new OrganizationNotFoundException(dto.getOrganizationId());

        if (dto.getParentId() != null) {
            boolean parentExists = repository.existsById(dto.getParentId());
            if (!parentExists) throw new TeamNotFoundException(dto.getParentId());
        }
        boolean existsByName = repository.existsByName(dto.getName());
        if (existsByName) throw new EntityAlreadyExistsException("Team already exists with name: " + dto.getName());
    }

    @Override
    public Boolean isExist(Long id) {
        return repository.existsById(id);
    }

}
