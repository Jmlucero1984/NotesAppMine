package jml.noteschallenge.notesapp.domain.nota;

import jml.noteschallenge.notesapp.domain.categoria.Categoria;

import java.util.Set;

public record DatosListadoNotas(
        Long id,
        String titulo,
        Set<Categoria> categorias,
        String cuerpo,
        String estado
) {

    public DatosListadoNotas(Nota nota) {
        this(nota.getId(), nota.getTitulo(), nota.getCategorias(), nota.getCuerpo(), nota.getArchivado()?"ARCHIVADO":"ACTIVO");
    }
}
