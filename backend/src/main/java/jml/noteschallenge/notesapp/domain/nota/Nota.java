package jml.noteschallenge.notesapp.domain.nota;

import jakarta.persistence.*;
import jml.noteschallenge.notesapp.domain.categoria.Categoria;
import jml.noteschallenge.notesapp.domain.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    private String cuerpo;
    private Boolean archivado;


    public Nota (DatosRegistroNota datosRegistroNota,Usuario usuario, Categoria categoria) {
        this.usuario = usuario;
        this.categoria = categoria;
        this.titulo = datosRegistroNota.titulo();
        this.cuerpo = datosRegistroNota.cuerpo();
        this.archivado = datosRegistroNota.estado().equals("ARCHIVADO")?true:false;

    }


    public void actualizarDatos(DatosActualizarNota datosActualizarNota,Categoria categoria) {
        if (datosActualizarNota.titulo() != null) {
            this.titulo = datosActualizarNota.titulo();
        }
        if (datosActualizarNota.cuerpo() != null) {
            this.cuerpo = datosActualizarNota.cuerpo();
        }
        if (datosActualizarNota.categoria() != null) {
            System.out.println("ACTUALIZA:"+categoria.getTitulo());
            this.categoria = categoria;
        }
        if (datosActualizarNota.estado() != null) {
            this.archivado = datosActualizarNota.estado().equals("ARCHIVADO")?true:false;
        }
    }

}
