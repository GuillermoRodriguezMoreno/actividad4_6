package org.iesvdm.actividad4_5.repository;

import org.iesvdm.actividad4_5.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    // Conteo de peliculas por categoria
    public long countPeliculasById(long id);

    // Filtro buscar por termino -> ?buscar=<nombre>
    public List<Categoria> findByNombreContaining(String nombre);

    // Filtro ordenar ascendente y descendente por nombre -> ?ordenar=asc|desc
    public List<Categoria> findAllByOrderByNombreAsc();
    public List<Categoria> findAllByOrderByNombreDesc();

    // Combinar filtros -> /categorias?buscar=<termino>&ordenar=asc|desc
    public List<Categoria> findByNombreContainingOrderByNombreAsc(String nombre);
    public List<Categoria> findByNombreContainingOrderByNombreDesc(String nombre);
}
