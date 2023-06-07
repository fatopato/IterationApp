package com.fatopato.iteration.service;

import com.fatopato.iteration.dto.TeamDto;
import com.fatopato.iteration.entity.Team;

import java.util.List;

public interface TeamService {
    TeamDto save(TeamDto dto);
    TeamDto getById(Long id);
    TeamDto update(TeamDto dto);
    void deleteById(Long id);

    TeamDto toDto(Team entity);

    Team toEntity(TeamDto dto);

    List<TeamDto> getAll();

    void validate(TeamDto dto, boolean isUpdate);

    Boolean isExist(Long id);
}
