package com.fatopato.iteration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExternalSquadDto extends BaseDto{
    private String name;
    private List<Long> squadMemberIds = new ArrayList<>();;
}
