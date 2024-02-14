package org.iesvdm.actividad4_2a.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;
    private String nombre;
    @JsonFormat(pattern = "yyyy-MM-dd:HH:mm:ss")
    private Date ultima_actualizacion;
    @ManyToMany(mappedBy = "categorias")
    @ToString.Exclude
    @JsonIgnore
    private Set<Pelicula> peliculas = new HashSet<>();

}
