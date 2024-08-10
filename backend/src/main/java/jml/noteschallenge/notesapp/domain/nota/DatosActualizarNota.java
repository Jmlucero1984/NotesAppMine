package jml.noteschallenge.notesapp.domain.nota;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import jml.noteschallenge.notesapp.domain.categoria.TituloColor;

import java.util.Set;


public record DatosActualizarNota(
        @NotNull
        Long id,
        @NotBlank
        String email_usuario,
        @NotBlank
        String titulo,
        @NotNull
        Set<TituloColor> categorias,
        @NotBlank
        String cuerpo,
        @NotBlank
        String estado) {

}
