package org.iesvdm.actividad4_2a.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;
    private String nombre;
    private String apellidos;
    @JsonFormat(pattern = "yyyy-MM-dd:HH:mm:ss")
    private Date ultima_actualizacion;
    @OneToMany(mappedBy = "actor")
    private Set<Pelicula_actor> peliculasActor = new HashSet<>();

}
