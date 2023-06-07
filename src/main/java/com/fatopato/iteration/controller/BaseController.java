package com.fatopato.iteration.controller;

import com.fatopato.iteration.dto.BaseDto;
import com.fatopato.iteration.dto.OrganizationDto;
import com.fatopato.iteration.exception.BaseBadRequestException;
import com.fatopato.iteration.exception.BaseEntityNotFoundException;
import com.fatopato.iteration.exception.EntityAlreadyExistsException;
import com.fatopato.iteration.service.BaseServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class BaseController <D extends BaseDto>{
    private final BaseServiceInterface service;

    public BaseController(BaseServiceInterface service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<D>> getAll() {
        List dtos = service.getAll();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity create(@RequestBody D dto) {
        return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity updateOrganization(@RequestBody D dto) {
        return ResponseEntity.ok(service.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrganization(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<String> handleEntityAlreadyExistsException(EntityAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    @ExceptionHandler(BaseEntityNotFoundException.class)
    public ResponseEntity<String> handleBaseEntityNotFoundException(BaseEntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    @ExceptionHandler(BaseBadRequestException.class)
    public ResponseEntity<String> handleBaseBadRequestException(BaseBadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
