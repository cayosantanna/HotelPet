package dao;

import model.ConfirmaçãoReserva;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class ConfirmaçãoReservaDAO implements IDao<ConfirmaçãoReserva> {
    private EntityManager entityManager;

    public ConfirmaçãoReservaDAO() {
        this.entityManager = entityManager;
    }

    @Override
    public List<ConfirmaçãoReserva> findAll() {
        return entityManager.createQuery("SELECT r FROM Relatorio r", ConfirmaçãoReserva.class).getResultList();
    }

    @Override
    public void save(ConfirmaçãoReserva relatorio) {
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
    public boolean delete(ConfirmaçãoReserva relatorio) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            ConfirmaçãoReserva found = entityManager.find(ConfirmaçãoReserva.class, relatorio.getId());
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
    public ConfirmaçãoReserva find(ConfirmaçãoReserva relatorio) {
        return entityManager.find(ConfirmaçãoReserva.class, relatorio.getId());
    }

    public ConfirmaçãoReserva findById(int id) {
        return entityManager.find(ConfirmaçãoReserva.class, id);
    }

    public void update(ConfirmaçãoReserva relatorio, ConfirmaçãoReserva novo) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            ConfirmaçãoReserva existing = entityManager.find(ConfirmaçãoReserva.class, relatorio.getId());
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
