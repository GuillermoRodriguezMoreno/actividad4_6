package org.iesvdm.actividad4_5.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.iesvdm.actividad4_5.domain.Pelicula;
import org.iesvdm.actividad4_5.repository.ICustomQueryPelicula;
import org.iesvdm.actividad4_5.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;
    @Autowired
    private ICustomQueryPelicula customQueryPelicula;
    @PersistenceContext
    EntityManager entityManager;

    public List<Pelicula> all() {
        return this.peliculaRepository.findAll();
    }

    public List<Pelicula> findAllPeliculasOrdenadas(String[] orden) {
        return this.customQueryPelicula.findAllPeliculasOrdenadas(orden);
    }

    @Transactional
    public Pelicula save(Pelicula pelicula) {

        this.peliculaRepository.save(pelicula);

        this.entityManager.refresh(pelicula);

        return pelicula;
    }

    public Pelicula one(Long id) {
        return this.peliculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pelicula no encontrada"));
    }

    public Pelicula replace(Long id, Pelicula pelicula) {

        return this.peliculaRepository.findById(id).map( p -> (id.equals(pelicula.getId())  ?
                        this.peliculaRepository.save(pelicula) : null))
                .orElseThrow(() -> new RuntimeException("Pelicula no encontrada"));

    }

    public void delete(Long id) {
        this.peliculaRepository.findById(id).map(p -> {this.peliculaRepository.delete(p);
                    return p;})
                .orElseThrow(() -> new RuntimeException("Pelicula no encontrada"));
    }
}
