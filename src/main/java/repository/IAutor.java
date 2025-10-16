package repository;

import entities.Autor;
import java.util.List;
import java.util.Optional;

public interface IAutor {
    Autor guardar(Autor autor);
    Optional<Autor> buscarPorId(Long id);
    List<Autor> listarTodos();
    void eliminar(Long id);
    Optional<Autor> buscarPorNombre(String nombre);
    List<Autor> buscarPorNacionalidad(String nacionalidad);
}