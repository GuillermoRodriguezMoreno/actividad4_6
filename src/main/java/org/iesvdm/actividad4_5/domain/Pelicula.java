package org.iesvdm.actividad4_5.domain;

import jakarta.persistence.*;
import lombok.*;

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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "pelicula_categoria",
            joinColumns = @JoinColumn(name = "pelicula_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    )
    private Set<Categoria> categorias = new HashSet<>();
    @OneToMany(mappedBy = "pelicula")
    private Set<Pelicula_actor> actores = new HashSet<>();

}
