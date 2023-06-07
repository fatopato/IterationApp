package com.fatopato.iteration.service;

import com.fatopato.iteration.dto.OrganizationDto;
import com.fatopato.iteration.entity.Organization;
import com.fatopato.iteration.entity.Team;
import com.fatopato.iteration.exception.EntityAlreadyExistsException;
import com.fatopato.iteration.repository.OrganizationRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl extends BaseService<OrganizationDto, Organization> implements OrganizationService {

    @NonNull
    private final OrganizationRepository repository;

    protected OrganizationServiceImpl(OrganizationRepository repository) {
        super(repository, "Organization");
        this.repository = repository;

    }

    @Override
    public OrganizationDto toDto(Organization entity) {
        OrganizationDto dto = new OrganizationDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setTeamIds(entity.getTeams().stream().map(Team::getId).collect(Collectors.toList()));
        return dto;
    }

    @Override
    public Organization toEntity(OrganizationDto dto) {
        Organization entity = new Organization();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setTeams(dto.getTeamIds().stream().map(Team::new).collect(Collectors.toList()));
        return entity;
    }

    @Override
    public void validate(OrganizationDto dto, boolean isUpdate) {
        boolean existsByName = repository.existsByName(dto.getName());
        if (existsByName) throw new EntityAlreadyExistsException("Organization already exists with name: " + dto.getName());
    }

}
