package org.iesvdm.actividad4_2a.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.actividad4_2a.domain.Categoria;
import org.iesvdm.actividad4_2a.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping({"", "/"})
    public List<Categoria> all() {
        log.info("Accediendo a todas las categorias.");
        return this.categoriaService.all();
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
}
