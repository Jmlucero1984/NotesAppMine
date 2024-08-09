package jml.noteschallenge.notesapp.domain.nota;

import jml.noteschallenge.notesapp.domain.categoria.Categoria;
import jml.noteschallenge.notesapp.domain.categoria.DatosRespuestaCategoria;

import java.util.Set;

public record DatosRespuestaNota(
        String email_usuario,
        String titulo,
        Set<Categoria> categorias,
        String cuerpo,
        String archivado
) {


}
