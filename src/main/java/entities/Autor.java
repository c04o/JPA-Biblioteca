package entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores",
        uniqueConstraints = @UniqueConstraint(name = "uk_autor_nombre", columnNames = "nombre"))
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(max = 120)
    @Column(nullable = false, length = 120)
    private String nombre;

    @Size(max = 80)
    @Column(length = 80)
    private String nacionalidad;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    // Autor (1) -> (N) Libro
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Libro> libros = new ArrayList<>();

    public Autor() {}

    public Autor(String nombre, String nacionalidad, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.fechaNacimiento = fechaNacimiento;
    }

    // Helpers de relación bidireccional
    public void addLibro(Libro libro) {
        libros.add(libro);
        libro.setAutor(this);
    }
    public void removeLibro(Libro libro) {
        libros.remove(libro);
        libro.setAutor(null);
    }

    // Getters/Setters
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getNacionalidad() { return nacionalidad; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public List<Libro> getLibros() { return libros; }

    public void setId(Long id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public void setLibros(List<Libro> libros) { this.libros = libros; }
}

