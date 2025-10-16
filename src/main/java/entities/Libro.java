package entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(max = 200)
    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(name = "anio_publicacion")
    private Integer anioPublicacion;

    // (N) Libro -> (1) Autor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_libro_autor"))
    @NotNull
    private Autor autor;

    // (N) Libro <-> (N) Categoria
    @ManyToMany
    @JoinTable(
            name = "libros_categorias",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id"),
            uniqueConstraints = @UniqueConstraint(name = "uk_libro_categoria", columnNames = {"libro_id","categoria_id"})
    )
    private List<Categoria> categorias = new ArrayList<>();

    public Libro() {}

    public Libro(String titulo, Integer anioPublicacion) {
        this.titulo = titulo;
        this.anioPublicacion = anioPublicacion;
    }

    // Helpers relación N-N
    public void addCategoria(Categoria c) {
        if (!categorias.contains(c)) {
            categorias.add(c);
            c.getLibros().add(this);
        }
    }
    public void removeCategoria(Categoria c) {
        if (categorias.remove(c)) {
            c.getLibros().remove(this);
        }
    }

    // Getters/Setters
    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public Integer getAnioPublicacion() { return anioPublicacion; }
    public Autor getAutor() { return autor; }
    public List<Categoria> getCategorias() { return categorias; }

    public void setId(Long id) { this.id = id; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setAnioPublicacion(Integer anioPublicacion) { this.anioPublicacion = anioPublicacion; }
    public void setAutor(Autor autor) { this.autor = autor; }
    public void setCategorias(List<Categoria> categorias) { this.categorias = categorias; }
}
