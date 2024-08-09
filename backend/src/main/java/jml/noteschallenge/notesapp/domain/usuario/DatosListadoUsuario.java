package jml.noteschallenge.notesapp.domain.usuario;

import jml.noteschallenge.notesapp.domain.categoria.Categoria;

public record DatosListadoUsuario(
        Long id,
        String nombre,
        String email
) {

    public DatosListadoUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getEmail());
    }
}
