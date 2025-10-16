package repository.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public abstract class GenericDAO<T> {

    protected final EntityManager em;
    private final Class<T> entityClass;

    public GenericDAO(EntityManager em, Class<T> entityClass) {
        this.em = em;
        this.entityClass = entityClass;
    }

    public T crear(T entidad) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(entidad);
            tx.commit();
            return entidad;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        }
    }

    public T buscar(Object id) {
        return em.find(entityClass, id);
    }

    public T actualizar(T entidad) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            T managed = em.merge(entidad);
            tx.commit();
            return managed;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        }
    }

    public void eliminar(Object id) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            T entidad = em.find(entityClass, id);
            if (entidad != null) {
                em.remove(entidad);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        }
    }

    public List<T> listarTodos() {
        return em.createQuery("FROM " + entityClass.getSimpleName(), entityClass)
                .getResultList();
    }
}