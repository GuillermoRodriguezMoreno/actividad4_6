package org.iesvdm.actividad4_2a.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Idioma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;
    private String nombre;
    private Date ultima_actualizacion;
    @OneToMany(mappedBy = "idioma")
    private Set<Pelicula> peliculas_idioma = new HashSet<>();
    @OneToMany(mappedBy = "idioma_original")
    private Set<Pelicula> peliculas_idioma_original = new HashSet<>();
}
