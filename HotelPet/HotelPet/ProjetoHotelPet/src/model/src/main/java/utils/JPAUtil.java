package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    private static final EntityManagerFactory entityManagerFactory;

    static {
        try {
            // Use o nome correto da unidade de persistência
            entityManagerFactory = Persistence.createEntityManagerFactory("exemplo-jpa");
        } catch (Throwable ex) {
            System.err.println("Erro ao criar EntityManagerFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}
