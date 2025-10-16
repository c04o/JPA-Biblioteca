package config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public final class JPAUtil {

    private static final String PU_NAME = "libreria";

    private static final EntityManagerFactory EMF =
            Persistence.createEntityManagerFactory(PU_NAME);

    private JPAUtil() { /* no instanciable */ }

    public static EntityManager getEntityManager() {
        return EMF.createEntityManager();
    }

    public static void close() {
        if (EMF != null && EMF.isOpen()) {
            EMF.close();
        }
    }
}
