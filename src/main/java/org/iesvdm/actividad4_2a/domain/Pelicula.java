package org.iesvdm.actividad4_2a.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;
    private String titulo;
    private String descripcion;
    @ManyToOne()
    private Idioma idioma_original;
    @ManyToOne()
    private Idioma idioma;
    @ManyToMany()
    private Set<Categoria> categorias = new HashSet<>();
    @OneToMany(mappedBy = "pelicula")
    private Set<Pelicula_actor> actores = new HashSet<>();

}
