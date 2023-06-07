package com.fatopato.iteration.repository;


import com.fatopato.iteration.entity.IterationItem;
import com.fatopato.iteration.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IterationItemRepository extends JpaRepository<IterationItem, Long> {
}
