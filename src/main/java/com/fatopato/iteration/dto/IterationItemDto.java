package com.fatopato.iteration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IterationItemDto extends BaseDto{
    private Long iterationId;
    private String title;
    private Boolean isCompleted = Boolean.FALSE;
    private List<Long> effortIds = new ArrayList<>();;
}
