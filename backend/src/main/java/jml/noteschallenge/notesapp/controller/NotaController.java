package jml.noteschallenge.notesapp.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jml.noteschallenge.notesapp.domain.categoria.Categoria;

import jml.noteschallenge.notesapp.domain.categoria.CategoriaRepository;
import jml.noteschallenge.notesapp.domain.categoria.TituloColor;
import jml.noteschallenge.notesapp.domain.nota.*;
import jml.noteschallenge.notesapp.domain.usuario.Usuario;
import jml.noteschallenge.notesapp.domain.usuario.UsuarioRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:3000","*"}, maxAge = 3600 )

@RestController
@RequestMapping("/notas")
public class NotaController {

    @Autowired
    private NotaRepository notaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;



    @PostMapping
    @Transactional
    public ResponseEntity registrarNota (@RequestBody @Valid DatosRegistroNota datosRegistroNota,
                                                                 UriComponentsBuilder uriComponentsBuilder) {

        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("USUARIO: "+usuario.getEmail()+" SOLICITA REGISTRAR NOTA");
        System.out.println(datosRegistroNota.categorias());

        Set<String> titulosCategoriasActualizar = datosRegistroNota.categorias().stream().map(t->t.titulo()).collect(Collectors.toSet());


        Set<Categoria> categorias = categoriaRepository
                .findAllByUsuarioId(usuario.getId())
                .stream()
                .filter(c->titulosCategoriasActualizar.contains(c.getTitulo())).collect(Collectors.toSet());
        Nota nota = notaRepository.save(new Nota(datosRegistroNota,usuario,categorias));

        return ResponseEntity.ok().build();
    }




    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarNota(@PathVariable Long id) {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("USUARIO: "+usuario.getEmail()+" SOLICITA ELIMINAR NOTA: "+id);
        Optional<Nota> notaEliminar = Optional.of(notaRepository.getReferenceById(id));
        if(notaEliminar.isPresent()) {
            var nota = notaEliminar.get();
            notaRepository.deleteByIdAndUsuarioId(nota.getId(), usuario.getId());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarNota(@RequestBody @Valid DatosActualizarNota datosActualizarNota) {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("USUARIO: "+usuario.getEmail()+" SOLICITA ACTUALZIAR NOTA: "+datosActualizarNota.id());
        System.out.println("NUEVO CATEGORIA: "+datosActualizarNota.categorias());
        System.out.println("NUEVO ESTAOD: "+datosActualizarNota.estado());
        Optional<Nota> notaActualizar =  notaRepository.findByIdAndUsuarioId(datosActualizarNota.id(), usuario.getId());

        if(notaActualizar.isPresent()) {
            Set<String> titulosCategoriasActualizar = datosActualizarNota.categorias().stream().map(t->t.titulo()).collect(Collectors.toSet());
            var nota = notaActualizar.get();

            Set<Categoria> categorias = categoriaRepository
                    .findAllByUsuarioId(usuario.getId())
                    .stream()
                    .filter(c->titulosCategoriasActualizar.contains(c.getTitulo())).collect(Collectors.toSet());

            System.out.println("LAS CATEGORIAS ENCONTRADAS");
            System.out.println(categorias);
            nota.actualizarDatos(datosActualizarNota,categorias);
            notaRepository.save(nota);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }



    /***************************/

    @GetMapping("/{id}")
    public ResponseEntity retornaDatosNota(@PathVariable Long id) {
        Optional<Nota> nota = Optional.of(notaRepository.getReferenceById(id));
        if(nota.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Nota foundedNota = nota.get();
        Set<TituloColor> categorias = foundedNota.getCategorias().stream().map(n->new TituloColor(n.getTitulo(),n.getColor())).collect(Collectors.toSet());

        var datosNota = new DatosRespuestaNota(
                foundedNota.getUsuario().getEmail(),
                foundedNota.getTitulo(),
                categorias,
                foundedNota.getCuerpo(),
                foundedNota.getArchivado()?"ARCHIVADO":"ACTIVO"
        );
        return ResponseEntity.ok(datosNota);
    }

    @GetMapping
    public List<DatosListadoNotas> listadoNotasPorUsuario () throws BadRequestException {

        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("USUARIO: "+usuario.getEmail()+" SOLICITA LISTADO NOTAS");

        if(usuario==null){

            throw new BadRequestException();
        }
        return notaRepository.findAllByUsuario_id(usuario.getId()).stream().map(DatosListadoNotas::new).toList();
    }
}
