package repository;

import entities.Categoria;
import java.util.List;
import java.util.Optional;

public interface ICategoria {
    Categoria guardar(Categoria categoria);
    Optional<Categoria> buscarPorId(Long id);
    List<Categoria> listarTodos();
    void eliminar(Long id);
    Optional<Categoria> buscarPorNombre(String nombre);
}