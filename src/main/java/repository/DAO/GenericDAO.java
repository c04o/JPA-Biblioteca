package repository.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public abstract class GenericDAO<T> {
    protected EntityManager em;
    private Class<T> entityClass;

    public GenericDAO(EntityManager em, Class<T> entityClass) {
        this.em = em;
        this.entityClass = entityClass;
    }

    public void crear(T entidad) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(entidad);
        tx.commit();
    }

    public T buscar(Object id) {
        return em.find(entityClass, id);
    }

    public void actualizar(T entidad) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(entidad);
        tx.commit();
    }

    public void eliminar(Object id) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        T entidad = em.find(entityClass, id);
        if (entidad != null) {
            em.remove(entidad);
        }
        tx.commit();
    }

    public List<T> listarTodos() {
        return em.createQuery("FROM " + entityClass.getSimpleName(), entityClass)
                .getResultList();
    }
}
