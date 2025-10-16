package repository.DAO;

import entities.Categoria;
import jakarta.persistence.EntityManager;

public class CategoriaDAO extends GenericDAO<Categoria> {
    public CategoriaDAO(EntityManager em) {
        super(em, Categoria.class);
    }
}
