package dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.HistoricoRh;

public class HistoricoRhDAO implements IDao<HistoricoRh> {
    private EntityManager entityManager;

    public HistoricoRhDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(HistoricoRh obj) {
        if (obj.getCpfRh() == null || obj.getCpfRh().isEmpty()) {
            throw new IllegalArgumentException("O campo CPF não pode estar vazio.");
        }

        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(obj);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public void update(HistoricoRh obj, HistoricoRh novo) {
        entityManager.getTransaction().begin();
        HistoricoRh existente = entityManager.find(HistoricoRh.class, obj.getId());

        if (existente != null) {
            existente.setCpfRh(novo.getCpfRh());
            existente.setAcao(novo.getAcao());
            entityManager.merge(existente);
        }

        entityManager.getTransaction().commit();
    }

    @Override
    public boolean delete(HistoricoRh obj) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            HistoricoRh toDelete = entityManager.find(HistoricoRh.class, obj.getId());
            if (toDelete != null) {
                entityManager.remove(toDelete);
                transaction.commit();
                return true;
            }
            transaction.rollback();
            return false;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public List<HistoricoRh> findAll() {
        TypedQuery<HistoricoRh> query = entityManager.createQuery("SELECT h FROM HistoricoRh h", HistoricoRh.class);
        return query.getResultList();
    }

    @Override
    public HistoricoRh find(HistoricoRh obj) {
        TypedQuery<HistoricoRh> query = entityManager.createQuery(
                "SELECT h FROM HistoricoRh h WHERE h.cpfRh = :cpfRh AND h.acao = :acao", HistoricoRh.class
        );
        query.setParameter("cpfRh", obj.getCpfRh());
        query.setParameter("acao", obj.getAcao());

        List<HistoricoRh> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    public HistoricoRh findById(Integer id) {
        return entityManager.find(HistoricoRh.class, id);
    }

    public List<HistoricoRh> findAllAcoes() {
        EntityManager em = entityManager.getEntityManagerFactory().createEntityManager();
        try {
            TypedQuery<HistoricoRh> query = em.createQuery("SELECT h FROM HistoricoRh h ORDER BY h.dataHora DESC", HistoricoRh.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
