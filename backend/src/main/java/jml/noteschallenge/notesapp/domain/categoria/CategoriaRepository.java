package jml.noteschallenge.notesapp.domain.categoria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {

    Categoria findByTitulo(String titulo);
    Page<Categoria> findAll(Pageable paginacion);


    List<Categoria> findAllByUsuario_id(@Param("id") Long id);

    Categoria findByTituloAndUsuarioId(String categoria, Long id);

    Set<Categoria> findAllByNotasId(Long id);

    Set<Categoria> findAllByUsuarioId(Long id);
}
