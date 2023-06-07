package com.fatopato.iteration.service;

import com.fatopato.iteration.dto.IterationItemDto;
import com.fatopato.iteration.entity.Iteration;
import com.fatopato.iteration.entity.IterationItem;
import com.fatopato.iteration.entity.IterationItemEffort;
import com.fatopato.iteration.entity.Team;
import com.fatopato.iteration.exception.BaseBadRequestException;
import com.fatopato.iteration.exception.EntityAlreadyExistsException;
import com.fatopato.iteration.repository.IterationItemRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class IterationItemServiceImpl extends BaseService<IterationItemDto, IterationItem> implements IterationItemService {
    private final IterationItemRepository repository;
    private final IterationService iterationService;

    protected IterationItemServiceImpl(IterationItemRepository repository, IterationService iterationService) {
        super(repository, "Iteration Item");
        this.repository = repository;
        this.iterationService = iterationService;
    }

    @Override
    public IterationItemDto toDto(IterationItem entity) {
        IterationItemDto dto = new IterationItemDto();
        dto.setId(entity.getId());
        dto.setIterationId(entity.getIteration().getId());
        dto.setIsCompleted(entity.isCompleted());
        dto.setTitle(entity.getTitle());
        dto.setEffortIds(entity.getEfforts().stream().map(IterationItemEffort::getId).collect(Collectors.toList()));
        return dto;
    }

    @Override
    public IterationItem toEntity(IterationItemDto dto) {
        IterationItem entity = new IterationItem();
        entity.setId(dto.getId());
        entity.setIteration(new Iteration(dto.getIterationId()));
        entity.setTitle(entity.getTitle());
        entity.setCompleted(dto.getIsCompleted());
        entity.setEfforts(dto.getEffortIds().stream().map(IterationItemEffort::new).collect(Collectors.toList()));
        return entity;
    }

    @Override
    public void validate(IterationItemDto dto, boolean isUpdate) {
        boolean iterationExists = iterationService.isExists(dto.getIterationId());
        if (!iterationExists) throw new BaseBadRequestException("Invalid iteration id: " + dto.getIterationId());
        boolean existsByIterationIdAndTitle = repository.existsByIterationIdAndTitle(dto.getIterationId(), dto.getTitle());
        if (existsByIterationIdAndTitle)
            throw new EntityAlreadyExistsException("Title  already exists in this iteration id");
    }
}
