package com.fatopato.iteration.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "team_member")
public class TeamMember extends BaseEntity{

    public TeamMember(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @Column(name = "is_outsource")
    private boolean isOutsource;

    @ManyToOne
    @JoinColumn(name = "external_squad_id")
    private ExternalSquad externalSquad;

    @OneToMany(mappedBy = "teamMember")
    private List<IterationItemEffort> efforts = new ArrayList<>();
}
