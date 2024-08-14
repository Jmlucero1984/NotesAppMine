package jml.noteschallenge.notesapp.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jml.noteschallenge.notesapp.domain.usuario.Usuario;
import jml.noteschallenge.notesapp.infra.errores.exceptions.AuthenticationErrorException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

   // @Value("${api.security.secret}")
    private String apiSecret;

    public String generarToken(Usuario usuario) {
        System.out.println("GENERAR TOKEN");
        try {
            Algorithm algorithm = Algorithm.HMAC256("apiSecret");
            return JWT.create()
                    .withIssuer("MyNotesApp")
                    .withSubject(usuario.getEmail())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }

    public String getSubject(String token) {
        System.out.println("GET SUBJECT");
        System.out.println("TOKEN: "+token);
        if (token == null) {
            throw new RuntimeException();
        }
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256("apiSecret");

            verifier = JWT.require(algorithm)
                    .withIssuer("MyNotesApp")
                    .build()
                    .verify(token);
            verifier.getSubject();
        } catch (JWTVerificationException exception) {
            System.out.println("------ JWT VERIFICATION EXCEPTION ------");
            System.out.println(exception.toString());


        }
        try {
        if (verifier == null) {

            throw new AuthenticationErrorException("VERIFIER IS NULL");
        }
            if(verifier.getSubject() == null) {

                throw new AuthenticationErrorException("VERIFIER.GET SUBJECT IS NULL");
            }
            return verifier.getSubject();
        } catch (AuthenticationErrorException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private Instant generarFechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}
