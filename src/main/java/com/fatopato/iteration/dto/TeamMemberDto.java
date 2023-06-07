package com.fatopato.iteration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamMemberDto extends BaseDto{
    private String name;
    private Long teamId;
    private Boolean isOutsource = Boolean.FALSE;
    private Long externalSquadId;
}
