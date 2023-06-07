package com.fatopato.iteration.service;

import com.fatopato.iteration.dto.BaseDto;
import com.fatopato.iteration.entity.BaseEntity;
import java.util.List;

public interface BaseServiceInterface<D extends BaseDto, E extends BaseEntity> {
    D save(D dto);
    D getById(Long id);
    D update(D dto);
    void deleteById(Long id);

    D toDto(E entity);

    E toEntity(D dto);
    List<D> getAll();

    Boolean isExists(Long id);

    void validate(D dto, boolean isUpdate);
}
