package com.fatopato.iteration.dto;

import com.fatopato.iteration.entity.IterationItem;
import com.fatopato.iteration.entity.TeamMember;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IterationItemEffortDto {
    private Long id;
    private Long iterationItemId;
    private Long teamMemberId;
    private Float effort;
}
