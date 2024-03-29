package org.iesvdm.actividad4_5.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.actividad4_5.domain.Categoria;
import org.iesvdm.actividad4_5.dto.CategoriaDTO;
import org.iesvdm.actividad4_5.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    // CRUD
    @GetMapping(value = {"", "/"}, params = {"!buscar", "!ordenar", "!pagina", "!tamanio"})
    public List<Categoria> all() {
        log.info("Accediendo a todas las categorias.");
        return this.categoriaService.all();
    }

    // All categorias con conteo de peliculas

    @GetMapping(value = "/count")
    public List<CategoriaDTO> allandPeliculasCount() {
        log.info("Accediendo a todas las categorias.");
        return this.categoriaService.findAllByIdAndAndPeliculasCount();
    }

    // Paginacion
    @GetMapping(value = {"", "/"}, params = {"!buscar", "!ordenar", "pagina", "tamanio"})
    public ResponseEntity<Map<String, Object>> allPaginated(@RequestParam (value = "pagina", defaultValue = "0") int pagina,
                                                            @RequestParam (value = "tamanio", defaultValue = "3") int tamanio) {
        log.info("Accediendo a todas las categorias paginadas.");
        Map<String, Object> responseAll = this.categoriaService.all(pagina, tamanio);
        return ResponseEntity.ok( responseAll);
    }

    @PostMapping({"", "/"})
    public Categoria newCategoria(@RequestBody Categoria categoria) {
        log.info("Guardando categoria: " + categoria);
        return this.categoriaService.save(categoria);
    }

    @GetMapping("/{id}")
    public Categoria one(@PathVariable Long id) {
        log.info("Accediendo a la categoria con id: " + id);
        return this.categoriaService.one(id);
    }

    @PutMapping("/{id}")
    public Categoria replaceCategoria(@PathVariable("id")Long id, @RequestBody Categoria categoria) {
        log.info("Reemplazando categoria con id: " + id + " por: " + categoria);
        return this.categoriaService.replace(id, categoria);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCategoria(@PathVariable Long id) {
        log.info("Borrando categoria con id: " + id);
        this.categoriaService.delete(id);
    }

    // Custom Queries

    //Conteo de peliculas por categoria
    @GetMapping("/count/{id}")
    public long countPeliculas(@PathVariable Long id) {
        log.info("Contando peliculas de la categoria con id: " + id);
        return this.categoriaService.countPeliculas(id);
    }
    //Filtro buscar por termino -> ?buscar=<nombre>
    @GetMapping(value = {"", "/"}, params = {"buscar"})
    public List<Categoria> findByNombre(@RequestParam String buscar) {
        log.info("Buscando categoria con nombre: " + buscar);
        return this.categoriaService.findByNombre(buscar);
    }
    //Filtro ordenar ascendente y descendente por nombre -> ?ordenar=asc|desc
    @GetMapping(value = {"", "/"}, params = {"ordenar"})
    public List<Categoria> findAllByNombre(@RequestParam String ordenar) {
        log.info("Ordenando categorias por nombre: " + ordenar);
        if (ordenar.equals("asc")) {
            return this.categoriaService.findAllByNombreAsc();
        } else if (ordenar.equals("desc")) {
            return this.categoriaService.findAllByNombreDesc();
        } else {
            throw new IllegalArgumentException("El parametro ordenar solo puede ser 'asc' o 'desc'.");
        }
    }
    //Combinar filtros -> /categorias?buscar=<termino>&ordenar=asc|desc
    @GetMapping(value = {"", "/"}, params = {"buscar", "ordenar"})
    public List<Categoria> findByNombreAscOrDesc(@RequestParam String buscar, @RequestParam String ordenar) {
        log.info("Buscando categoria con nombre: " + buscar + " y ordenando por: " + ordenar);
        if (ordenar.equals("asc")) {
            return this.categoriaService.findByNombreAsc(buscar);
        } else if (ordenar.equals("desc")) {
            return this.categoriaService.findByNombreDesc(buscar);
        } else {
            throw new IllegalArgumentException("El parametro ordenar solo puede ser 'asc' o 'desc'.");
        }
    }
    /* ******* Buscar por Query dinamica ***************
    @GetMapping(value = {"", "/"}, params = {"buscar", "ordenar"})
    public List<Categoria> findByNombre(@RequestParam Optional<String> buscar, @RequestParam Optional<String> ordenar) {
        log.info("Buscando categoria con nombre: " + buscar + " y ordenando por: " + ordenar);
        return this.categoriaService.findByNombre(buscar, ordenar);
    }
     */
}
