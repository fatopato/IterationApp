package com.fatopato.iteration.service;

import com.fatopato.iteration.dto.ExternalSquadDto;
import com.fatopato.iteration.entity.ExternalSquad;
import com.fatopato.iteration.entity.TeamMember;
import com.fatopato.iteration.exception.EntityAlreadyExistsException;
import com.fatopato.iteration.repository.ExternalSquadRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ExternalSquadServiceImpl extends BaseService<ExternalSquadDto, ExternalSquad> implements ExternalSquadService {

    private final ExternalSquadRepository repository;

    protected ExternalSquadServiceImpl(ExternalSquadRepository repository) {
        super(repository, "External Squad");
        this.repository = repository;
    }

    @Override
    public ExternalSquadDto toDto(ExternalSquad entity) {
        ExternalSquadDto dto = new ExternalSquadDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSquadMemberIds(entity.getSquadMembers().stream().map(e -> e.getExternalSquad().getId()).collect(Collectors.toList()));
        return dto;
    }

    @Override
    public ExternalSquad toEntity(ExternalSquadDto dto) {
        ExternalSquad entity = new ExternalSquad();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setSquadMembers(dto.getSquadMemberIds().stream().map(TeamMember::new).collect(Collectors.toList()));
        return entity;
    }

    @Override
    public void validate(ExternalSquadDto dto, boolean isUpdate) {
        boolean existsByName = repository.existsByName(dto.getName());
        if (existsByName) throw new EntityAlreadyExistsException("External Squad already exists with name: " + dto.getName());
    }
}
