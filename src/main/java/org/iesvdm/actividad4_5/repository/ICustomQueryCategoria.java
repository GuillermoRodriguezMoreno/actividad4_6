package org.iesvdm.actividad4_5.repository;

import org.iesvdm.actividad4_5.domain.Categoria;

import java.util.List;
import java.util.Optional;

public interface ICustomQueryCategoria {

    public List<Categoria> buscarCategoriaOrdenada(Optional<String> buscar, Optional<String> ordenar);
}
