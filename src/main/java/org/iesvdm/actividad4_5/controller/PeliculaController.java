package org.iesvdm.actividad4_5.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.actividad4_5.domain.Categoria;
import org.iesvdm.actividad4_5.domain.Pelicula;
import org.iesvdm.actividad4_5.service.PeliculaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    private final PeliculaService peliculaService;

    public PeliculaController(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @GetMapping(value = {"","/"}, params = { "!orden", "!pagina", "!tamanio"})
    public List<Pelicula> all() {
        log.info("Accediendo a todas las películas");
        return this.peliculaService.all();
    }

    // Paginacion con multiples parametros
    @GetMapping(value = {"", "/"}, params = {"!orden", "pagina"})
    public ResponseEntity<Map<String, Object>> allPaginated(@RequestParam String[] pagina) {
        log.info("Accediendo a todas las películas paginadas.");
        Map<String, Object> responseAll = this.peliculaService.all(pagina);
        return ResponseEntity.ok( responseAll);
    }

    // All peliculas ordenadas por multimples parametros
    @GetMapping(value = {"", "/"}, params = {"orden"})
    public List<Pelicula> findAllPeliculasOrdenadas(@RequestParam String[] orden ) {
        log.info("Buscando pelicula con orden: " + Arrays.toString(orden));
        return this.peliculaService.findAllPeliculasOrdenadas(orden);
    }

    @PostMapping({"","/"})
    public Pelicula newPelicula(@RequestBody Pelicula pelicula) {
        log.info("Creando una pelicula = " + pelicula);
        return this.peliculaService.save(pelicula);
    }

    @GetMapping("/{id}")
    public Pelicula one(@PathVariable("id") Long id) {
        log.info("Buscar pelicula con id = " + id);
        return this.peliculaService.one(id);
    }

    @PutMapping("/{id}")
    public Pelicula replacePelicula(@PathVariable("id") Long id, @RequestBody Pelicula pelicula) {
        log.info("Actualizar pelicula con id = " + id + "\n pelicula = " + pelicula);
        return this.peliculaService.replace(id, pelicula);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePelicula(@PathVariable("id") Long id) {
        this.peliculaService.delete(id);
    }
}
