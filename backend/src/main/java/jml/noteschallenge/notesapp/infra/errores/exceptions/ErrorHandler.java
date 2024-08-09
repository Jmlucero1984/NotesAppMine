package jml.noteschallenge.notesapp.infra.errores.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity handleEntityAlreadyExistsException(EntityAlreadyExistsException ex) {
       // return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(UnregisteredUserException.class)
    public ResponseEntity handleUnregisteredUserException(UnregisteredUserException ex) {
        // return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        return ResponseEntity.badRequest().body(extractBetweenHammers(ex.getMessage()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity handlePSQLException(DataIntegrityViolationException ex) throws JsonProcessingException {

        String exMessage = ex.getMostSpecificCause().getMessage();
        if(exMessage.contains("unique constraint \"usuarios_email_key\"")) {
            return ResponseEntity.badRequest().body("Ya existe un usuario con esos datos");
        }


        return ResponseEntity.badRequest().body("Ha ocurrido un error con las credenciales");
    }


    public String extractBetweenHammers(String message) {
        String regex = "¬(.*?)¬";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(message);

        if(matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }
}
