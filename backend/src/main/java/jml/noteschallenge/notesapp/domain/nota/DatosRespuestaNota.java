package jml.noteschallenge.notesapp.domain.nota;

import jml.noteschallenge.notesapp.domain.categoria.Categoria;
import jml.noteschallenge.notesapp.domain.categoria.DatosRespuestaCategoria;

public record DatosRespuestaNota(
        String email_usuario,
        String titulo,
        String categoria,
        String cuerpo,
        String archivado
) {

    public DatosRespuestaNota(Nota nota){
        this(nota.getUsuario().getEmail(),nota.getTitulo(), nota.getCategoria().getTitulo(), nota.getCuerpo(),nota.getArchivado()?"ARCHIVADO":"ACTIVO");
    }
}
