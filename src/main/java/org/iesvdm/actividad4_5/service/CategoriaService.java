package org.iesvdm.actividad4_5.service;

import org.iesvdm.actividad4_5.domain.Categoria;
import org.iesvdm.actividad4_5.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    // Repository
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    // CRUD
    public List<Categoria> all() {
        return this.categoriaRepository.findAll();
    }

    public Categoria save(Categoria categoria) {
        return this.categoriaRepository.save(categoria);
    }

    public Categoria one(Long id) {
      return this.categoriaRepository.findById(id).orElse(null);
    }

    public Categoria replace(Long id, Categoria categoria) {
        return this.categoriaRepository.findById(id).map( c -> (id.equals(categoria.getId())  ?
                this.categoriaRepository.save(categoria) : null))
                .orElse(null);
    }

    public void delete(Long id) {
        this.categoriaRepository.deleteById(id);
    }

    // Custom Queries

    //Conteo de peliculas por categoria
    public long countPeliculas(long id) {
        return this.categoriaRepository.countPeliculasById(id);
    }

    //Filtro buscar por termino -> ?buscar=<nombre>
    public List<Categoria> findByNombre(String nombre) {
        return this.categoriaRepository.findByNombreContainingIgnoreCase(nombre);
    }

    //Filtro ordenar ascendente y descendente por nombre -> ?ordenar=asc|desc
    public List<Categoria> findAllByNombreAsc() {
        return this.categoriaRepository.findAllByOrderByNombreAsc();
    }

    public List<Categoria> findAllByNombreDesc() {
        return this.categoriaRepository.findAllByOrderByNombreDesc();
    }

    //Combinar filtros -> /categorias?buscar=<termino>&ordenar=asc|desc
    public List<Categoria> findByNombreAsc(String nombre) {
        return this.categoriaRepository.findByNombreContainingIgnoreCaseOrderByNombreAsc(nombre);
    }

    public List<Categoria> findByNombreDesc(String nombre) {
        return this.categoriaRepository.findByNombreContainingIgnoreCaseOrderByNombreDesc(nombre);
    }
}
