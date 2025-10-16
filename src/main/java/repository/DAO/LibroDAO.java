package repository.DAO;

import entities.Libro;
import jakarta.persistence.EntityManager;
import java.util.List;

public class LibroDAO extends GenericDAO<Libro> {
    public LibroDAO(EntityManager em) {
        super(em, Libro.class);
    }

    public List<Libro> listarConAutorYCategorias() {
        return em.createQuery(
                "SELECT DISTINCT l FROM Libro l " +
                        "JOIN FETCH l.autor " +
                        "LEFT JOIN FETCH l.categorias", Libro.class
        ).getResultList();
    }
}
