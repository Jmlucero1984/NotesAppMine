package jml.noteschallenge.notesapp.domain.categoria;

public record DatosListadoCategoriaSimple(
        String titulo
) {
    public DatosListadoCategoriaSimple(Categoria categoria) {
        this(categoria.getTitulo());
    }
}
