package repository.DAO;

import entities.Autor;
import jakarta.persistence.EntityManager;
import repository.IAutor;

import java.util.List;
import java.util.Optional;

public class AutorDAO extends GenericDAO<Autor> implements IAutor {

    public AutorDAO(EntityManager em) {
        super(em, Autor.class);
    }

    @Override
    public Autor guardar(Autor autor) {
        return (autor.getId() == null) ? crear(autor) : actualizar(autor);
    }

    @Override
    public Optional<Autor> buscarPorId(Long id) {
        return Optional.ofNullable(buscar(id));
    }

    @Override
    public List<Autor> listarTodos() {
        return super.listarTodos();
    }

    @Override
    public void eliminar(Long id) {
        super.eliminar(id);
    }

    @Override
    public Optional<Autor> buscarPorNombre(String nombre) {
        List<Autor> r = em.createQuery(
                        "SELECT a FROM Autor a WHERE UPPER(a.nombre) = :n",
                        Autor.class
                ).setParameter("n", nombre.toUpperCase())
                .setMaxResults(1)
                .getResultList();

        return r.isEmpty() ? Optional.empty() : Optional.of(r.get(0));
    }

    @Override
    public List<Autor> buscarPorNacionalidad(String nacionalidad) {
        return em.createQuery(
                        "SELECT a FROM Autor a " +
                                "WHERE UPPER(a.nacionalidad) = :n " +
                                "ORDER BY a.nombre",
                        Autor.class
                ).setParameter("n", nacionalidad.toUpperCase())
                .getResultList();
    }
}