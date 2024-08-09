package jml.noteschallenge.notesapp.infra.security;

public record DatosJWTToken(
        String name,
        String email,
        String jwTtoken) {
}
