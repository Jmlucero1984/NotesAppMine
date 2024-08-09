package jml.noteschallenge.notesapp.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DatosAutenticacionUsuario(
        @NotBlank
        String email,
        @NotBlank
        String contraseña
) {
}
