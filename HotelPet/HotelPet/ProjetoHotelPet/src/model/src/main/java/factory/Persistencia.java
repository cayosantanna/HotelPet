package factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Persistencia {
    
    // Criando um EntityManagerFactory com o nome da unidade de persistência
    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("exemplo-jpa"); // Nome da unidade de persistência definido no persistence.xml

    // Método para obter o EntityManager
    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
    public static void beginTransaction(EntityManager em) {
    if (!em.getTransaction().isActive()) {
        em.getTransaction().begin();
    }
}

public static void commitTransaction(EntityManager em) {
    if (em.getTransaction().isActive()) {
        em.getTransaction().commit();
    }
}

public static void rollbackTransaction(EntityManager em) {
    if (em.getTransaction().isActive()) {
        em.getTransaction().rollback();
    }
}
public static void closeEntityManager(EntityManager em) {
    if (em != null && em.isOpen()) {
        em.close();
    }
}
}
