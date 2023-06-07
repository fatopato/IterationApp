package com.fatopato.iteration.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "iteration_item_effort")
public class IterationItemEffort {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "iteration_item_id")
    private IterationItem iterationItem;

    @ManyToOne
    @JoinColumn(name = "team_member_id")
    private TeamMember teamMember;

    @Column(name = "effort")
    private float effort;

    public IterationItemEffort(Long id) {
        this.id = id;
    }
}
