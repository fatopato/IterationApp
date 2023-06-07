package com.fatopato.iteration.dto;

import com.fatopato.iteration.entity.Organization;
import com.fatopato.iteration.entity.Team;
import com.fatopato.iteration.entity.TeamMember;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamDto {
    private Long id;
    private Long parentId;
    private String name;
    private Long organizationId;
    private List<Long> teamMemberIds = new ArrayList<>();
}
