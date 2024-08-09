package jml.noteschallenge.notesapp.domain.nota;

import jakarta.persistence.*;
import jml.noteschallenge.notesapp.domain.categoria.Categoria;
import jml.noteschallenge.notesapp.domain.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Table(name= "notas")
@Entity(name = "Nota")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")

public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    private String titulo;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "notas_categorias",
            joinColumns = @JoinColumn(name = "nota_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private Set<Categoria> categorias;
    private String cuerpo;
    private Boolean archivado;


    public Nota (DatosRegistroNota datosRegistroNota,Usuario usuario, Set<Categoria> categorias) {
        this.usuario = usuario;
        this.categorias = categorias;
        this.titulo = datosRegistroNota.titulo();
        this.cuerpo = datosRegistroNota.cuerpo();
        this.archivado = datosRegistroNota.estado().equals("ARCHIVADO")?true:false;

    }


    public void actualizarDatos(DatosActualizarNota datosActualizarNota,Set<Categoria> categorias) {
        if (datosActualizarNota.titulo() != null) {
            this.titulo = datosActualizarNota.titulo();
        }
        if (datosActualizarNota.cuerpo() != null) {
            this.cuerpo = datosActualizarNota.cuerpo();
        }
        if (datosActualizarNota.categorias() != null) {

            this.categorias = categorias;
        }
        if (datosActualizarNota.estado() != null) {
            this.archivado = datosActualizarNota.estado().equals("ARCHIVADO")?true:false;
        }
    }

}
