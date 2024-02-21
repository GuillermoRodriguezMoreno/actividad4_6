package org.iesvdm.actividad4_5.repository;

import org.iesvdm.actividad4_5.domain.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
}
