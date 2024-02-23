package org.iesvdm.actividad4_5.repository;
import org.iesvdm.actividad4_5.domain.Pelicula;

import java.util.List;

public interface ICustomQueryPelicula {

    public List<Pelicula> findAllPeliculasOrdenadas(String[] orden);
}
