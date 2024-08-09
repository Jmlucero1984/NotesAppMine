package jml.noteschallenge.notesapp.domain.nota;

import jakarta.transaction.Transactional;
import jml.noteschallenge.notesapp.domain.categoria.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NotaRepository extends JpaRepository<Nota,Long> {

    Page<Nota> findAll(Pageable paginacion);
    List<Nota> findAllByUsuario_id(@Param("id") Long id);
    @Transactional
    void deleteByIdAndUsuarioId(Long id, Long usuarioId);



    Optional<Nota> findByIdAndUsuarioId(Long id,Long usuarioId);
}
