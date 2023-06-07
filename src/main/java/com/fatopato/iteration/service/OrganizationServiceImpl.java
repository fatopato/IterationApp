package com.fatopato.iteration.service;

import com.fatopato.iteration.dto.OrganizationDto;
import com.fatopato.iteration.entity.Organization;
import com.fatopato.iteration.entity.Team;
import com.fatopato.iteration.exception.EntityAlreadyExistsException;
import com.fatopato.iteration.exception.OrganizationNotFoundException;
import com.fatopato.iteration.exception.TeamNotFoundException;
import com.fatopato.iteration.repository.OrganizationRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    @NonNull
    private final OrganizationRepository repository;

    @Override
    public OrganizationDto save(OrganizationDto dto) {
        validate(dto, false);
        return toDto(repository.save(toEntity(dto)));
    }

    @Override
    public OrganizationDto getById(Long id) {
        return repository.findById(id).map(this::toDto).orElseThrow(() -> new OrganizationNotFoundException("Organization not found with the id: " + id));
    }

    @Override
    public OrganizationDto update(OrganizationDto dto) {
        repository.findById(dto.getId()).orElseThrow(() -> new OrganizationNotFoundException("Organization not found with the id: " + dto.getId()));
        return toDto(repository.save(toEntity(dto)));
    }

    @Override
    public void deleteById(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new OrganizationNotFoundException("Organization not found with the id: " + id));
        repository.deleteById(id);
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
    public List<OrganizationDto> getAll() {
        return repository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public Boolean isExists(Long id) {
        return repository.existsById(id);
    }

    @Override
    public void validate(OrganizationDto dto, boolean isUpdate) {
        boolean existsByName = repository.existsByName(dto.getName());
        if (existsByName) throw new EntityAlreadyExistsException("Organization already exists with name: " + dto.getName());
    }


}
