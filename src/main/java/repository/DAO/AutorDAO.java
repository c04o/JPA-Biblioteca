package repository.DAO;

import entities.Autor;
import jakarta.persistence.EntityManager;

public class AutorDAO extends GenericDAO<Autor> {
    public AutorDAO(EntityManager em) {
        super(em, Autor.class);
    }
}
