package org.iesvdm.actividad4_5.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.iesvdm.actividad4_5.domain.Categoria;
import org.iesvdm.actividad4_5.domain.Pelicula;
import org.iesvdm.actividad4_5.repository.ICustomQueryPelicula;
import org.iesvdm.actividad4_5.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    // Paginado
    public Map<String, Object> all(String[] pagina) {

        int pag = Integer.parseInt(pagina[0]);
        int tam = Integer.parseInt(pagina[1]);

        Pageable paginado = PageRequest.of(pag, tam, Sort.by("titulo").ascending());
        Page<Pelicula> pageAll = this.peliculaRepository.findAll(paginado);

        Map<String, Object> response = new HashMap<>();
        response.put("peliculas", pageAll.getContent());
        response.put("paginaActual", pageAll.getNumber());
        response.put("totalElementos", pageAll.getTotalElements());
        response.put("totalPaginas", pageAll.getTotalPages());

        return response;
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
