package jml.noteschallenge.notesapp.controller;

import jakarta.validation.Valid;
import jml.noteschallenge.notesapp.domain.usuario.DatosAutenticacionUsuario;
import jml.noteschallenge.notesapp.domain.usuario.Usuario;
import jml.noteschallenge.notesapp.domain.usuario.UsuarioRepository;
import jml.noteschallenge.notesapp.infra.errores.exceptions.UnregisteredUserException;
import jml.noteschallenge.notesapp.infra.security.DatosJWTToken;
import jml.noteschallenge.notesapp.infra.security.TokenService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = {"*"}, maxAge = 3600 )
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario) throws BadRequestException {

        System.out.println("AUTHENTICAR USUARIO");
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.email(),datosAutenticacionUsuario.contraseña());
        try {
            var usuarioAutenticado = authenticationManager.authenticate(authToken);

            var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());

            Optional<Usuario> usuario = Optional.ofNullable(usuarioRepository.findByEmail(datosAutenticacionUsuario.email()));
            return ResponseEntity.ok( new DatosJWTToken(usuario.get().getNombre(),datosAutenticacionUsuario.email(),JWTtoken));
        } catch(Exception e ) {

           throw new UnregisteredUserException("Error al autenticar, verifique los datos ingresados...");
        }



    }
}
