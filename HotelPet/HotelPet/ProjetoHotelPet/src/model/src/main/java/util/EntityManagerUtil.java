package util;

import javax.persistence.EntityManager;

public class EntityManagerUtil {
    public static EntityManager getEntityManager() {
        return JPAUtil.getEntityManagerFactory().createEntityManager();
    }
}
