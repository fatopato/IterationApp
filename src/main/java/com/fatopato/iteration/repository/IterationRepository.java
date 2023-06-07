package com.fatopato.iteration.repository;


import com.fatopato.iteration.entity.Iteration;
import com.fatopato.iteration.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IterationRepository extends JpaRepository<Iteration, Long> {
    List<Iteration> findAllByTeamId(Long teamId);
}
