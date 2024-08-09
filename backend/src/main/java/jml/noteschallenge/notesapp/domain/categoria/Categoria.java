package jml.noteschallenge.notesapp.domain.categoria;

import jakarta.persistence.*;
import jml.noteschallenge.notesapp.domain.nota.Nota;
import jml.noteschallenge.notesapp.domain.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Table(name= "categorias")
@Entity(name = "Categoria")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")

public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    private String titulo;
    @ManyToMany(mappedBy = "categorias")
    private Set<Nota> notas;

    public Categoria(Usuario usuario, String titulo) {
        this.usuario=usuario;
        this.titulo = titulo;
    }
}
