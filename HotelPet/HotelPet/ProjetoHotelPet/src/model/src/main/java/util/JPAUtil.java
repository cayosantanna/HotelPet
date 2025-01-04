package util;
import javax.persistence.EntityManager; /*Erro*/
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    private static final EntityManagerFactory entityManagerFactory;

    static {
        try {
            System.out.println("Tentando criar EntityManagerFactory...");
            entityManagerFactory = Persistence.createEntityManagerFactory("exemplo-jpa");
            System.out.println("EntityManagerFactory criado com sucesso.");
        } catch (Throwable ex) {
            System.err.println("Erro ao criar EntityManagerFactory: " + ex);
            ex.printStackTrace();  // Detalhamento do erro
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    // Adicionando o método para criar EntityManager
    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
