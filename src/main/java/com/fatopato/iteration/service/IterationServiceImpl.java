package com.fatopato.iteration.service;

import com.fatopato.iteration.dto.IterationDto;
import com.fatopato.iteration.entity.Iteration;
import com.fatopato.iteration.entity.IterationItem;
import com.fatopato.iteration.entity.Team;
import com.fatopato.iteration.exception.BaseBadRequestException;
import com.fatopato.iteration.repository.IterationRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IterationServiceImpl extends BaseService<IterationDto, Iteration> implements IterationService {
    private final IterationRepository repository;
    private final TeamService teamService;

    protected IterationServiceImpl(IterationRepository repository, TeamService teamService) {
        super(repository, "Iteration");
        this.repository = repository;
        this.teamService = teamService;
    }

    @Override
    public IterationDto toDto(Iteration entity) {
        IterationDto dto = new IterationDto();
        dto.setId(entity.getId());
        dto.setTeamId(entity.getTeam().getId());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setIsActive(entity.isActive());
        dto.setIterationItemIds(entity.getIterationItems().stream().map(IterationItem::getId).collect(Collectors.toList()));
        return dto;
    }

    @Override
    public Iteration toEntity(IterationDto dto) {
        Iteration entity = new Iteration();
        entity.setId(dto.getId());
        entity.setTeam(new Team(dto.getTeamId()));
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setActive(dto.getIsActive());
        entity.setIterationItems(dto.getIterationItemIds().stream().map(IterationItem::new).collect(Collectors.toList()));
        return entity;
    }

    @Override
    public void validate(IterationDto dto, boolean isUpdate) {
        boolean teamExists = teamService.isExists(dto.getTeamId());
        if (!teamExists) throw new BaseBadRequestException("Invalid team id: " + dto.getTeamId());
        validateDates(dto);
    }

    private void validateDates(IterationDto dto) {

        if (dto.getStartDate().isAfter(dto.getEndDate()))
            throw new BaseBadRequestException("Start date can not be before end date");

        List<Iteration> activeIterations = repository.findAllByTeamId(dto.getTeamId()).stream()
                .filter(Iteration::isActive).collect(Collectors.toList());
        activeIterations.stream().filter(i ->
            dto.getStartDate().isBefore(i.getEndDate())
                    && dto.getEndDate().isAfter(i.getStartDate())
        ).findAny().ifPresent(iteration -> {throw new BaseBadRequestException("There are active iterations between " +
                "these dates for team id: " + dto.getTeamId());});
    }

    @Override
    public List<IterationDto> getAllActiveIterations(Long teamId) {
        return repository.findAllByTeamId(teamId).stream().filter(Iteration::isActive)
                .map(this::toDto).collect(Collectors.toList());
    }
}
