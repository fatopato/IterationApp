package com.fatopato.iteration.dto;

import com.fatopato.iteration.entity.ExternalSquad;
import com.fatopato.iteration.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamMemberDto {
    private Long id;
    private String name;
    private Long teamId;
    private Boolean isOutsource = Boolean.FALSE;
    private Long externalSquadId;
}
