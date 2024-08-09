package jml.noteschallenge.notesapp.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jml.noteschallenge.notesapp.domain.categoria.Categoria;
import jml.noteschallenge.notesapp.domain.categoria.CategoriaRepository;
import jml.noteschallenge.notesapp.domain.usuario.DatosRegistroUsuario;
import jml.noteschallenge.notesapp.domain.usuario.DatosRespuestaUsuario;
import jml.noteschallenge.notesapp.domain.usuario.Usuario;
import jml.noteschallenge.notesapp.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name="bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @CrossOrigin(origins = {"http://localhost:3000","*"}, maxAge = 3600 )
    @PostMapping
    public ResponseEntity<DatosRespuestaUsuario> registrarUsuario (@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario, UriComponentsBuilder uriComponentsBuilder) {
        Usuario usuario = usuarioRepository.save(new Usuario(datosRegistroUsuario,bCryptPasswordEncoder.encode(datosRegistroUsuario.contraseña())));

        DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getContraseña()
        );
        Categoria general = new Categoria(usuario, "General");
        Categoria importante = new Categoria(usuario,"Importante" );
        categoriaRepository.save(general);
        categoriaRepository.save(importante);

        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaUsuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity retornaDatosUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.getReferenceById(id);
        var datosUsuario = new DatosRespuestaUsuario(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getContraseña(),
                usuario.getContraseña()
        );
        return ResponseEntity.ok(datosUsuario);
    }

}
