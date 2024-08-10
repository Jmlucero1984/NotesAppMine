package jml.noteschallenge.notesapp.infra.errores.exceptions;

public class AuthenticationErrorException extends RuntimeException {
    public AuthenticationErrorException(String message) {
        super(message);
    }
}