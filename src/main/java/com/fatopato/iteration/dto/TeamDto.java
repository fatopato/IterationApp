package com.fatopato.iteration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeamDto extends BaseDto{
    private Long parentId;
    private String name;
    private Long organizationId;
    private List<Long> teamMemberIds = new ArrayList<>();
}
