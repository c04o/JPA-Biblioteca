package entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categorias",
        uniqueConstraints = @UniqueConstraint(name = "uk_categoria_nombre", columnNames = "nombre"))
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(max = 80)
    @Column(nullable = false, length = 80)
    private String nombre;

    @ManyToMany(mappedBy = "categorias")
    private List<Libro> libros = new ArrayList<>();

    public Categoria() {}
    public Categoria(String nombre) { this.nombre = nombre; }

    // Getters/Setters
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public List<Libro> getLibros() { return libros; }

    public void setId(Long id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setLibros(List<Libro> libros) { this.libros = libros; }
}
