package org.iesvdm.actividad4_2a.repository;

import org.iesvdm.actividad4_2a.domain.Idioma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdiomaRepository extends JpaRepository<Idioma, Long> {
}
