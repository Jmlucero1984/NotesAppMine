package jml.noteschallenge.notesapp.infra.errores.exceptions;


public class UnregisteredUserException extends RuntimeException {
    public UnregisteredUserException(String message) {
        super(message);
    }
}