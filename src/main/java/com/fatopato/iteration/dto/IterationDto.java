package com.fatopato.iteration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IterationDto extends BaseDto{
    private Long teamId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isActive = Boolean.FALSE;
    private List<Long> iterationItemIds = new ArrayList<>();;
}
