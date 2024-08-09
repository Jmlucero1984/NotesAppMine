package jml.noteschallenge.notesapp.domain.usuario;

import jml.noteschallenge.notesapp.domain.categoria.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Usuario findByEmail(String email);

    Page<Usuario> findAll(Pageable paginacion);


}
