package org.iesvdm.actividad4_2a;

import org.iesvdm.actividad4_2a.domain.*;
import org.iesvdm.actividad4_2a.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashSet;

@SpringBootTest
class Actividad42aApplicationTests {

    @Autowired
    PeliculaRepository peliculaRepository;
    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    IdiomaRepository idiomaRepository;
    @Autowired
    ActorRepository actorRepository;
    @Autowired
    PeliculaActorRepository peliculaActorRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void crearPelicula() {

        // Creo pelicula
        Pelicula pelicula = new Pelicula(0, "El Padrino", "descripcion", null, null, new HashSet<>(), new HashSet<>());

        // Creo idiomas
        Idioma idioma = new Idioma(0, "Español", new Date(), new HashSet<>(), new HashSet<>());
        Idioma idioma_original = new Idioma(0, "Ingles", new Date(), new HashSet<>(), new HashSet<>());

        // Guardo en la base de datos
        idiomaRepository.save(idioma);
        idiomaRepository.save(idioma_original);

        // Setteo idiomas a pelicula
        pelicula.setIdioma(idioma);
        pelicula.setIdioma_original(idioma_original);

        // Guardo pelicula
        peliculaRepository.save(pelicula);
    }

    @Test
    void crearCategoria() {

        Categoria categoria = new Categoria(0, "Drama", new Date(),  new HashSet<>());
        Categoria categoria1 = new Categoria(0, "Musical", new Date(),  new HashSet<>());
        Categoria categoria2 = new Categoria(0, "Policiaca", new Date(),  new HashSet<>());
        Categoria categoria3 = new Categoria(0, "Terror", new Date(),  new HashSet<>());
        Categoria categoria4 = new Categoria(0, "Comedia", new Date(),  new HashSet<>());

        categoriaRepository.save(categoria);
        categoriaRepository.save(categoria1);
        categoriaRepository.save(categoria2);
        categoriaRepository.save(categoria3);
        categoriaRepository.save(categoria4);
    }

    @Test
    void guardarManyToManyPeliculaCategoria() {

        Pelicula pelicula = new Pelicula(0, "Star Wars", "descripcion", null, null, new HashSet<>(), new HashSet<>());
        peliculaRepository.save(pelicula);

        Categoria categoria = new Categoria(0, "Drama", new Date(), new HashSet<>());
        categoriaRepository.save(categoria);
        Categoria categoria2 = new Categoria(0, "Crimen", new Date(),new HashSet<>());
        categoriaRepository.save(categoria2);

        pelicula.getCategorias().add(categoria);
        pelicula.getCategorias().add(categoria2);
        categoria.getPeliculas().add(pelicula);
        categoria2.getPeliculas().add(pelicula);

        peliculaRepository.save(pelicula);
        categoriaRepository.save(categoria);
        categoriaRepository.save(categoria2);
    }

    @Test
    void guardarOneToManyPeliculaActor() {

        // Creo pelicula
        Pelicula pelicula = new Pelicula(0, "El Padrino", "descripcion", null, null, new HashSet<>(), new HashSet<>());

        // Creo idiomas
        Idioma idioma = new Idioma(0, "Español", new Date(), new HashSet<>(), new HashSet<>());
        Idioma idioma_original = new Idioma(0, "Ingles", new Date(), new HashSet<>(), new HashSet<>());

        // Guardo en la base de datos
        idiomaRepository.save(idioma);
        idiomaRepository.save(idioma_original);

        // Setteo idiomas a pelicula
        pelicula.setIdioma(idioma);
        pelicula.setIdioma_original(idioma_original);

        // Guardo pelicula
        peliculaRepository.save(pelicula);

        // Creo actores
        Actor actor = new Actor(0, "Harrison", "Ford", new Date(), new HashSet<>());
        actorRepository.save(actor);
        Actor actor2 = new Actor(0, "Mark", "Hamill", new Date(), new HashSet<>());
        actorRepository.save(actor2);

        // Creo pelicula_actor
        Pelicula_actor peliculaActor = new Pelicula_actor(0, actor, pelicula, new Date());
        peliculaActorRepository.save(peliculaActor);
        Pelicula_actor peliculaActor2 = new Pelicula_actor(0, actor2, pelicula, new Date());
        peliculaActorRepository.save(peliculaActor2);
    }

}
