package repository.DAO;

import entities.Libro;
import jakarta.persistence.EntityManager;
import repository.ILibro;

import java.util.List;
import java.util.Optional;

public class LibroDAO extends GenericDAO<Libro> implements ILibro {

    public LibroDAO(EntityManager em) {
        super(em, Libro.class);
    }

    @Override
    public Libro guardar(Libro libro) {
        return (libro.getId() == null) ? crear(libro) : actualizar(libro);
    }

    @Override
    public Optional<Libro> buscarPorId(Long id) {
        return Optional.ofNullable(buscar(id));
    }

    @Override
    public List<Libro> listarTodos() {
        return super.listarTodos();
    }

    @Override
    public void eliminar(Long id) {
        super.eliminar(id);
    }

    @Override
    public List<Libro> buscarPorTitulo(String titulo) {
        return em.createQuery(
                        "SELECT l FROM Libro l " +
                                "WHERE UPPER(l.titulo) LIKE :t " +
                                "ORDER BY l.titulo",
                        Libro.class
                ).setParameter("t", "%" + titulo.toUpperCase() + "%")
                .getResultList();
    }

    @Override
    public List<Libro> buscarPorAutor(Long autorId) {
        return em.createQuery(
                        "SELECT l FROM Libro l " +
                                "WHERE l.autor.id = :a " +
                                "ORDER BY l.titulo",
                        Libro.class
                ).setParameter("a", autorId)
                .getResultList();
    }

    @Override
    public List<Libro> buscarPorAnioPublicacion(Integer anio) {
        return em.createQuery(
                        "SELECT l FROM Libro l " +
                                "WHERE l.anioPublicacion = :y " +
                                "ORDER BY l.titulo",
                        Libro.class
                ).setParameter("y", anio)
                .getResultList();
    }

    @Override
    public List<Libro> buscarPorCategoria(Long categoriaId) {
        return em.createQuery(
                        "SELECT DISTINCT l FROM Libro l " +
                                "JOIN l.categorias c " +
                                "WHERE c.id = :c " +
                                "ORDER BY l.titulo",
                        Libro.class
                ).setParameter("c", categoriaId)
                .getResultList();
    }

    /** Extra útil para listar todo con relaciones y evitar N+1 */
    public List<Libro> listarConAutorYCategorias() {
        return em.createQuery(
                "SELECT DISTINCT l FROM Libro l " +
                        "JOIN FETCH l.autor " +
                        "LEFT JOIN FETCH l.categorias " +
                        "ORDER BY l.titulo",
                Libro.class
        ).getResultList();
    }
}