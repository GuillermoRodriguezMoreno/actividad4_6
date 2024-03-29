package org.iesvdm.actividad4_5.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.iesvdm.actividad4_5.domain.Categoria;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CategoriaDTO extends Categoria{

    private long conteo;

    public CategoriaDTO(Categoria categoria, long conteo){
        super(categoria.getId(), categoria.getNombre(), categoria.getUltima_actualizacion(), categoria.getPeliculas());
        this.conteo = conteo;
    }
}
