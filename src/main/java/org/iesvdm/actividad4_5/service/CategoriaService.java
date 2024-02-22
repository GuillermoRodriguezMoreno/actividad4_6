package org.iesvdm.actividad4_5.service;

import org.iesvdm.actividad4_5.domain.Categoria;
import org.iesvdm.actividad4_5.domain.Pelicula;
import org.iesvdm.actividad4_5.repository.CategoriaCustomRepositoryImpl;
import org.iesvdm.actividad4_5.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CategoriaService {

    // Repository
    private final CategoriaRepository categoriaRepository;
    private final CategoriaCustomRepositoryImpl categoriaCustomRepository;

    public CategoriaService(CategoriaRepository categoriaRepository, CategoriaCustomRepositoryImpl categoriaCustomRepository) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaCustomRepository = categoriaCustomRepository;
    }

    // CRUD
    public List<Categoria> all() {
        return this.categoriaRepository.findAll();
    }

    // Paginacion
    public Map<String, Object> all(int pagina, int tamanio) {

        Pageable paginado = PageRequest.of(pagina, tamanio, Sort.by("nombre").ascending());
        Page<Categoria> pageAll = this.categoriaRepository.findAll(paginado);

        Map<String, Object> response = new HashMap<>();
        response.put("categorias", pageAll.getContent());
        response.put("paginaActual", pageAll.getNumber());
        response.put("totalElementos", pageAll.getTotalElements());
        response.put("totalPaginas", pageAll.getTotalPages());

        return response;
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

    public List<Categoria> findByNombre(Optional<String> buscar, Optional<String> ordenar) {
        return this.categoriaCustomRepository.buscarCategoriaOrdenada(buscar, ordenar);
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
