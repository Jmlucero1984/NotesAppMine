package jml.noteschallenge.notesapp.domain.categoria;

public record DatosListadoCategorias(
        Long id,
        String titulo
) {

    public DatosListadoCategorias(Categoria categoria) {
        this(categoria.getId(), categoria.getTitulo());
    }
}
