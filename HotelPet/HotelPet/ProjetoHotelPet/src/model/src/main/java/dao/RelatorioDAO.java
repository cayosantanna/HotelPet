package dao;

import model.Relatorio;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class RelatorioDAO implements IDao<Relatorio> {
    private EntityManager entityManager;

    public RelatorioDAO() {
        this.entityManager = entityManager;
    }

    @Override
    public List<Relatorio> findAll() {
        return entityManager.createQuery("SELECT r FROM Relatorio r", Relatorio.class).getResultList();
    }

    @Override
    public void save(Relatorio relatorio) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(relatorio);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            // Log the exception here for debugging purposes
            throw new RuntimeException("Error saving Relatorio", e);
        }
    }

    @Override
    public boolean delete(Relatorio relatorio) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Relatorio found = entityManager.find(Relatorio.class, relatorio.getId());
            if (found != null) {
                entityManager.remove(found);
                transaction.commit();
                return true;
            }
            // Optionally, log or throw an exception if not found
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            // Log the exception here for debugging purposes
        }
        return false;
    }

    @Override
    public Relatorio find(Relatorio relatorio) {
        return entityManager.find(Relatorio.class, relatorio.getId());
    }

    public Relatorio findById(int id) {
        return entityManager.find(Relatorio.class, id);
    }

    public void update(Relatorio relatorio, Relatorio novo) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Relatorio existing = entityManager.find(Relatorio.class, relatorio.getId());
            if (existing != null) {
                existing.setCpfUsuario(novo.getCpfUsuario());
                existing.setDataCheckIn(novo.getDataCheckIn());
                existing.setDataCheckOut(novo.getDataCheckOut());
                existing.setDataRealizacaoReserva(novo.getDataRealizacaoReserva());
                existing.setPet(novo.getPet());
                existing.setValorPago(novo.getValorPago());
                existing.setCheckBoxAlimentacaoEspecial(novo.isCheckBoxAlimentacaoEspecial());
                existing.setCheckBoxBanho(novo.isCheckBoxBanho());
                existing.setCheckBoxPasseio(novo.isCheckBoxPasseio());
                existing.setCheckBoxTosa(novo.isCheckBoxTosa());

                entityManager.merge(existing);
                transaction.commit();
            } else {
                // Log or throw exception if 'existing' is null
            }
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            // Log the exception here for debugging purposes
            throw new RuntimeException("Error updating Relatorio", e);
        }
    }
}
