package repository;

import entities.Libro;
import java.util.List;
import java.util.Optional;

public interface ILibro {
    Libro guardar(Libro libro);
    Optional<Libro> buscarPorId(Long id);
    List<Libro> listarTodos();
    void eliminar(Long id);
    List<Libro> buscarPorTitulo(String titulo);
    List<Libro> buscarPorAutor(Long autorId);
    List<Libro> buscarPorAnioPublicacion(Integer anio);
    List<Libro> buscarPorCategoria(Long categoriaId);
}