package com.fatopato.iteration.service;

import com.fatopato.iteration.dto.OrganizationDto;
import com.fatopato.iteration.entity.Organization;

import java.util.List;

public interface OrganizationService {
    OrganizationDto save(OrganizationDto dto);
    OrganizationDto getById(Long id);
    OrganizationDto update(OrganizationDto dto);
    void deleteById(Long id);

    OrganizationDto toDto(Organization entity);

    Organization toEntity(OrganizationDto dto);
    List<OrganizationDto> getAll();

    Boolean isExists(Long id);

    void validate(OrganizationDto dto, boolean isUpdate);
}
