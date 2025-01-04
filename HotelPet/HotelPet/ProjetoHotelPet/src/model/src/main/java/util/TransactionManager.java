package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class TransactionManager {
    
    public static void beginTransaction(EntityManager em) {
        EntityTransaction transaction = em.getTransaction();
        if (!transaction.isActive()) {
            transaction.begin();
        }
    }
    
    public static void commitTransaction(EntityManager em) {
        EntityTransaction transaction = em.getTransaction();
        if (transaction.isActive() && !transaction.getRollbackOnly()) {
            transaction.commit();
        }
    }
    
    public static void rollbackTransaction(EntityManager em) {
        EntityTransaction transaction = em.getTransaction();
        if (transaction.isActive()) {
            transaction.rollback();
        }
    }
}
