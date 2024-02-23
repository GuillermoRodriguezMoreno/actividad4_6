package org.iesvdm.actividad4_5.repository;

import org.iesvdm.actividad4_5.domain.Categoria;
import org.iesvdm.actividad4_5.dto.CategoriaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    // Conteo de peliculas por categoria

    // No funciona sin la anotacion @Query, ya que necesita hacer el join explicitamente
    @Query("SELECT COUNT(p) FROM Categoria c JOIN c.peliculas p WHERE c.id = :categoriaId")
    long countPeliculasById(@Param("categoriaId") long categoriaId);

    // Aplicacion Query con DTO
    @Query("SELECT new org.iesvdm.actividad4_5.dto.CategoriaDTO(C, (SELECT COUNT(p) FROM Categoria c JOIN c.peliculas p WHERE c.id = C.id)) from Categoria C")
    public List<CategoriaDTO> findAllByIdAndAndPeliculasCount();
    // Filtro buscar por termino -> ?buscar=<nombre>
    public List<Categoria> findByNombreContainingIgnoreCase(String nombre);
    //
    // Filtro ordenar ascendente y descendente por nombre -> ?ordenar=asc|desc
    public List<Categoria> findAllByOrderByNombreAsc();
    public List<Categoria> findAllByOrderByNombreDesc();

    // Combinar filtros -> /categorias?buscar=<termino>&ordenar=asc|desc
    public List<Categoria> findByNombreContainingIgnoreCaseOrderByNombreAsc(String nombre);
    public List<Categoria> findByNombreContainingIgnoreCaseOrderByNombreDesc(String nombre);
}
