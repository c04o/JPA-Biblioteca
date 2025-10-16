package repository.DAO;

import entities.Categoria;
import jakarta.persistence.EntityManager;
import repository.ICategoria;

import java.util.List;
import java.util.Optional;

public class CategoriaDAO extends GenericDAO<Categoria> implements ICategoria {

    public CategoriaDAO(EntityManager em) {
        super(em, Categoria.class);
    }

    @Override
    public Categoria guardar(Categoria categoria) {
        return (categoria.getId() == null) ? crear(categoria) : actualizar(categoria);
    }

    @Override
    public Optional<Categoria> buscarPorId(Long id) {
        return Optional.ofNullable(buscar(id));
    }

    @Override
    public List<Categoria> listarTodos() {
        return super.listarTodos();
    }

    @Override
    public void eliminar(Long id) {
        super.eliminar(id);
    }

    @Override
    public Optional<Categoria> buscarPorNombre(String nombre) {
        List<Categoria> r = em.createQuery(
                        "SELECT c FROM Categoria c WHERE UPPER(c.nombre) = :n",
                        Categoria.class
                ).setParameter("n", nombre.toUpperCase())
                .setMaxResults(1)
                .getResultList();

        return r.isEmpty() ? Optional.empty() : Optional.of(r.get(0));
    }
}
