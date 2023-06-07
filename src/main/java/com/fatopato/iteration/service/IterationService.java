package com.fatopato.iteration.service;

import com.fatopato.iteration.dto.ExternalSquadDto;
import com.fatopato.iteration.dto.IterationDto;
import com.fatopato.iteration.entity.ExternalSquad;
import com.fatopato.iteration.entity.Iteration;

import java.util.List;

public interface IterationService extends BaseServiceInterface<IterationDto, Iteration>{

    List<IterationDto> getAllActiveIterations(Long teamId);
}
