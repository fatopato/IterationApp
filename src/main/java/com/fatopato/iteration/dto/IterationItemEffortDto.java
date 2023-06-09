package com.fatopato.iteration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IterationItemEffortDto extends BaseDto{
    private Long iterationItemId;
    private Long teamMemberId;
    private Float effort;
}
