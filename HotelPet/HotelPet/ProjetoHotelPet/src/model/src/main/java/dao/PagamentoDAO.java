package dao;

import javax.persistence.EntityManager;
import model.Pagamento;
import util.EntityManagerUtil;

public class PagamentoDAO {

    public void save(Pagamento pagamento) {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(pagamento);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;  // Re-lançamento da exceção
        } finally {
            entityManager.close();
        }
    }
}

