package com.fatopato.iteration.repository;


import com.fatopato.iteration.entity.ExternalSquad;
import com.fatopato.iteration.entity.IterationItemEffort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalSquadRepository extends JpaRepository<ExternalSquad, Long> {
    boolean existsByName(String name);
}
