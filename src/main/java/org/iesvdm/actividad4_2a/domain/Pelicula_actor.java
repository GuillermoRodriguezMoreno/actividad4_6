package org.iesvdm.actividad4_2a.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Pelicula_actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;
    @ManyToOne()
    private Actor actor;
    @ManyToOne
    private Pelicula pelicula;
    @JsonFormat(pattern = "yyyy-MM-dd:HH:mm:ss")
    private Date ultima_actualizacion;
}
