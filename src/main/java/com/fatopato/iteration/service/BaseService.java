package com.fatopato.iteration.service;

import com.fatopato.iteration.dto.BaseDto;
import com.fatopato.iteration.entity.BaseEntity;
import com.fatopato.iteration.exception.BaseBadRequestException;
import com.fatopato.iteration.exception.BaseEntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseService <D extends BaseDto, E extends BaseEntity> {
    private final JpaRepository<E, Long> repository;
    private final String entityName;
    

    protected BaseService(JpaRepository<E, Long> repository, String entityName) {
        this.repository = repository;
        this.entityName = entityName;
    }
    
    public D save (D dto) {
        if (dto.getId() != null) throw new BaseBadRequestException("Id must be null at save");
        validate(dto, false);
        return toDto(repository.save(toEntity(dto)));
    }
    public D getById(Long id) {
        return repository.findById(id).map(this::toDto).orElseThrow(() -> new BaseEntityNotFoundException(entityName, id));
    }
    public D update(D dto) {
        repository.findById(dto.getId()).orElseThrow(() -> new BaseEntityNotFoundException(entityName, dto.getId()));
        return toDto(repository.save(toEntity(dto)));
    }

    public void deleteById(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new BaseEntityNotFoundException(entityName, id));
        repository.deleteById(id);
    }

    public List<D> getAll() {
        return repository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public Boolean isExists(Long id) {
        return repository.existsById(id);
    }
    
    abstract void validate(D dto, boolean isUpdate);
    abstract D toDto(E entity);
    abstract E toEntity(D dto);
}
