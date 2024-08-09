package jml.noteschallenge.notesapp.domain.nota;

import jml.noteschallenge.notesapp.domain.categoria.Categoria;
import jml.noteschallenge.notesapp.domain.categoria.TituloColor;

import java.util.Set;
import java.util.stream.Collectors;

public record DatosListadoNotas(
        Long id,
        String titulo,
        Set<TituloColor> categorias,
        String cuerpo,
        String estado
) {

    public DatosListadoNotas(Nota nota) {
        this(
                nota.getId(),
                nota.getTitulo(),
                nota.getCategorias().stream().map(n->new TituloColor(n.getTitulo(),n.getColor())).collect(Collectors.toSet()),
                nota.getCuerpo(),
                nota.getArchivado()?"ARCHIVADO":"ACTIVO");
    }
}
