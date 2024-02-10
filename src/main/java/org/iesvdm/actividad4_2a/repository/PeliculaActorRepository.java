package org.iesvdm.actividad4_2a.repository;

import org.iesvdm.actividad4_2a.domain.Pelicula_actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaActorRepository extends JpaRepository<Pelicula_actor, Long> {
}
