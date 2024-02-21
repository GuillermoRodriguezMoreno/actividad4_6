package org.iesvdm.actividad4_5.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Idioma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;
    private String nombre;
    @JsonFormat(pattern = "yyyy-MM-dd:HH:mm:ss")
    private Date ultima_actualizacion;
    @OneToMany(mappedBy = "idioma")
    @JsonIgnore
    private Set<Pelicula> peliculas_idioma = new HashSet<>();
    @OneToMany(mappedBy = "idioma_original")
    @JsonIgnore
    private Set<Pelicula> peliculas_idioma_original = new HashSet<>();
}
