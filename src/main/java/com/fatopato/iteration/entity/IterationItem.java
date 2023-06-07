package com.fatopato.iteration.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "iteration_item")
public class IterationItem extends BaseEntity{

    public IterationItem(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "iteration_id")
    private Iteration iteration;

    @Column(name = "title")
    private String title;

    @Column(name = "is_completed")
    private boolean isCompleted;

    @OneToMany(mappedBy = "iterationItem")
    private List<IterationItemEffort> efforts = new ArrayList<>();

}
