package jml.noteschallenge.notesapp.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DatosRegistroUsuario (
        @NotBlank
        String nombre,
        @NotBlank
        @Email(message ="¬Debe ingresar un email válido¬")
        String email,
        @NotBlank
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
                message = "¬La contraseña debe tener al menos 8 caracteres, incluyendo letras, números y al menos un carácter especial.¬")
        String contraseña)
 {

}
