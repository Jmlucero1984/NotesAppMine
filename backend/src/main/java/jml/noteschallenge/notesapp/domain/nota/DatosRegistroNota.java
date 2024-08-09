package jml.noteschallenge.notesapp.domain.nota;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record DatosRegistroNota(
    @NotBlank
    String email_usuario,
    @NotBlank
    String titulo,
    @NotBlank
    Set<String> categorias,
    @NotBlank
    String cuerpo,
    @NotBlank
    String estado) {

}
