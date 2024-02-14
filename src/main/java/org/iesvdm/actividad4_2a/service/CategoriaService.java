package org.iesvdm.actividad4_2a.service;

import org.iesvdm.actividad4_2a.domain.Categoria;
import org.iesvdm.actividad4_2a.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> all() {
        return this.categoriaRepository.findAll();
    }

    public Categoria save(Categoria categoria) {
        return this.categoriaRepository.save(categoria);
    }

    public Categoria one(Long id) {
      return this.categoriaRepository.findById(id).orElse(null);
    }

    public Categoria replace(Long id, Categoria categoria) {
        return this.categoriaRepository.findById(id).map( c -> (id.equals(categoria.getId())  ?
                this.categoriaRepository.save(categoria) : null))
                .orElse(null);
    }

    public void delete(Long id) {
        this.categoriaRepository.deleteById(id);
    }
}
