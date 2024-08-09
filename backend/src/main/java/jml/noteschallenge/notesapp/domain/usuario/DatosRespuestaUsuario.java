package jml.noteschallenge.notesapp.domain.usuario;

import jml.noteschallenge.notesapp.domain.categoria.Categoria;

public record DatosRespuestaUsuario(
        Long id,
        String nombre,
        String email,
        String contraseña
) {


}
