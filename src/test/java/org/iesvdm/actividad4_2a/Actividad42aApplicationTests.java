package org.iesvdm.actividad4_2a;

import org.iesvdm.actividad4_2a.domain.Categoria;
import org.iesvdm.actividad4_2a.domain.Pelicula;
import org.iesvdm.actividad4_2a.repository.CategoriaRepository;
import org.iesvdm.actividad4_2a.repository.PeliculaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;

@SpringBootTest
class Actividad42aApplicationTests {

    @Autowired
    PeliculaRepository peliculaRepository;
    @Autowired
    CategoriaRepository categoriaRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void crearPelicula() {

        Pelicula pelicula = new Pelicula(0, "El Padrino", new HashSet<>());
        peliculaRepository.save(pelicula);
    }

    @Test
    void crearCategoria() {

        Categoria categoria = new Categoria(0, "Drama", new HashSet<>());
        categoriaRepository.save(categoria);
    }

    @Test
    void guardarManyToMany() {

        Pelicula pelicula = new Pelicula(0, "El Padrino", new HashSet<>());
        peliculaRepository.save(pelicula);

        Categoria categoria = new Categoria(0, "Drama", new HashSet<>());
        categoriaRepository.save(categoria);
        Categoria categoria2 = new Categoria(0, "Crimen", new HashSet<>());
        categoriaRepository.save(categoria2);

        pelicula.getCategorias().add(categoria);
        pelicula.getCategorias().add(categoria2);
        categoria.getPeliculas().add(pelicula);
        categoria2.getPeliculas().add(pelicula);

        peliculaRepository.save(pelicula);
        categoriaRepository.save(categoria);
        categoriaRepository.save(categoria2);
    }

}
