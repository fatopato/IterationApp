package com.fatopato.iteration.repository;


import com.fatopato.iteration.entity.Team;
import com.fatopato.iteration.entity.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {
    boolean existsByName(String name);
}
