package org.iesvdm.actividad4_5.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.iesvdm.actividad4_5.domain.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PeliculaCustomRepositoryImpl implements ICustomQueryPelicula{

    @Autowired
    private EntityManager em;

    @Override
    public List<Pelicula> findAllPeliculasOrdenadas(String[] orden) {
        // Si no hay orden, devuelvo todas las peliculas
        if(orden==null && orden.length==0){
            return this.em.createQuery("SELECT p FROM Pelicula p", Pelicula.class).getResultList();

        }else{
            StringBuilder queryBuilder = new StringBuilder("SELECT p FROM Pelicula p ORDER BY ");

            // Recorro parametros de orden
            for (String o: orden) {
                // Añado al String quitando la ","
                queryBuilder.append("p." + o.replaceAll(",", " "));
                // Añado la coma para concatenar el siguiente campo
                queryBuilder.append(", ");
            }

            // Borro la coma y el espacio final
            queryBuilder.delete(queryBuilder.length()-2, queryBuilder.length());
            // Creo la query
            Query query = em.createQuery(queryBuilder.toString(), Pelicula.class);

            return query.getResultList();
        }
    }
}
