package jml.noteschallenge.notesapp.controller;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jml.noteschallenge.notesapp.domain.categoria.*;

import jml.noteschallenge.notesapp.domain.usuario.Usuario;
import jml.noteschallenge.notesapp.domain.usuario.UsuarioRepository;
import jml.noteschallenge.notesapp.infra.errores.exceptions.EntityAlreadyExistsException;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600 )
@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;


    @PostMapping
    @Transactional
    public ResponseEntity registrarCategoria (@RequestBody @Valid DatosRegistroCategoria datosRegistroCategoria,
                                         UriComponentsBuilder uriComponentsBuilder) {

        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("USUARIO: "+usuario.getEmail()+" SOLICITA REGISTRAR CATEGORIA CON TITULO: "+datosRegistroCategoria.titulo());
        Optional<Categoria> categoria = Optional.ofNullable(categoriaRepository.findByTituloAndUsuarioId(datosRegistroCategoria.titulo(), usuario.getId()));
        if(categoria.isPresent()){

            throw new EntityAlreadyExistsException("Ya existe esa categoría para tu usuario en la Base de Datos");
        }
         categoriaRepository.save(new Categoria(usuario,datosRegistroCategoria.titulo(), datosRegistroCategoria.color()));


        return ResponseEntity.ok().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity retornaDatosCategoria(@PathVariable Long id) {
        Categoria categoria = categoriaRepository.getReferenceById(id);
        var datosCategoria = new DatosRespuestaCategoria(
                categoria.getTitulo(),
                categoria.getColor()


        );
        return ResponseEntity.ok(datosCategoria);
    }

    @GetMapping
    public List<DatosRespuestaCategoria> listadoCategoriasPorUsuario () throws BadRequestException {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("USUARIO: "+usuario.getEmail()+" SOLICITA LISTADO CATEGORIAS");

        if(usuario==null){

            throw new BadRequestException();
        }

        return categoriaRepository.findAllByUsuario_id(usuario.getId()).stream().map(t->new DatosRespuestaCategoria(t.getTitulo(),t.getColor())).toList();
    }
/*
    @GetMapping
    public List<DatosListadoCategorias> listadoCategorias () {
        return categoriaRepository.findAll().stream().map(DatosListadoCategorias::new).toList();
    }*/

}

