package jml.noteschallenge.notesapp.domain.nota;

import jml.noteschallenge.notesapp.domain.categoria.Categoria;

public record DatosListadoNotas(
        Long id,
        String titulo,
        String categoria,
        String cuerpo,
        String estado
) {

    public DatosListadoNotas(Nota nota) {
        this(nota.getId(), nota.getTitulo(), nota.getCategoria().getTitulo(), nota.getCuerpo(), nota.getArchivado()?"ARCHIVADO":"ACTIVO");
    }
}
