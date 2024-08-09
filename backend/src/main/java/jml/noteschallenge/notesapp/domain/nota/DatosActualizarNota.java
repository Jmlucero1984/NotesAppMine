package jml.noteschallenge.notesapp.domain.nota;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record DatosActualizarNota(
        @NotNull
        Long id,
        @NotBlank
        String email_usuario,
        @NotBlank
        String titulo,
        @NotBlank
        String categoria,
        @NotBlank
        String cuerpo,
        @NotBlank
        String estado) {

}
