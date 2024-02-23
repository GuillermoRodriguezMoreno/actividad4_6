package org.iesvdm.actividad4_5.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.iesvdm.actividad4_5.domain.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoriaCustomRepositoryImpl implements ICustomQueryCategoria {

    @Autowired
    private EntityManager em;

    // Implementacion con JQPL
    @Override
    public List<Categoria> buscarCategoriaOrdenada(Optional<String> buscar, Optional<String> ordenar) {
        StringBuilder queryBuilder = new StringBuilder("SELECT c FROM Categoria c");
        if (buscar.isPresent()) {
            queryBuilder.append(" WHERE c.nombre LIKE :nombre");
        }
        if (ordenar.isPresent()) {
            if (buscar.isPresent() && ordenar.get().equalsIgnoreCase("asc")) {
                queryBuilder.append(" ORDER BY c.nombre ASC");
            } else if (buscar.isPresent() && ordenar.get().equalsIgnoreCase("desc")) {
                queryBuilder.append(" ORDER BY c.nombre DESC");
            }
        }
        Query query = em.createQuery(queryBuilder.toString(), Categoria.class);
        if (buscar.isPresent()) {
            query.setParameter("nombre", "%" + buscar.get() + "%");
        }
        return query.getResultList();
    }
}
