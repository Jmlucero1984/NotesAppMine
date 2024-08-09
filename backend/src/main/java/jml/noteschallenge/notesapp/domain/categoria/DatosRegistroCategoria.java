package jml.noteschallenge.notesapp.domain.categoria;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroCategoria (
        @NotBlank
        String titulo,
        @NotBlank
        String color
){
}
