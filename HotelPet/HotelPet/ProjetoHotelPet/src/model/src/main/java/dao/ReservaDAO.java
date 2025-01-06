package dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.Reserva;
import util.EntityManagerUtil;
import java.util.List;
import javax.persistence.EntityTransaction;
import util.JPAUtil;

public class ReservaDAO implements IDao<Reserva> {
    @Override
    public List<Reserva> findAll() {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            TypedQuery<Reserva> query = entityManager.createNamedQuery("Reserva.findAll", Reserva.class);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao buscar todas as reservas: " + e.getMessage());
            return null;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
        public List<Reserva> findReservasSemCheckout() {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            TypedQuery<Reserva> query = entityManager.createQuery(
                "SELECT r FROM Reserva r WHERE r.checkOut IS NULL", Reserva.class
            );
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    public void save(Reserva reserva) {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(reserva);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }

    // Método para buscar uma reserva por ID
    @Override
    public Reserva find(Reserva reserva) {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            return entityManager.find(Reserva.class, reserva.getId());
        } catch (Exception e) {
            System.out.println("Erro ao buscar a reserva: " + e.getMessage());
            return null;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    // Método para buscar uma reserva pelo nome do pet
    public List<Reserva> findByNomePet(String nomePet) {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            TypedQuery<Reserva> query = entityManager.createNamedQuery("Reserva.findByNomePet", Reserva.class);
            query.setParameter("nomePet", "%" + nomePet + "%");
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao buscar reserva por nome do pet: " + e.getMessage());
            return null;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    // Método para buscar uma reserva pelo ID
    public Reserva findById(int id) {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            return entityManager.find(Reserva.class, id);
        } catch (Exception e) {
            System.out.println("Erro ao buscar reserva por ID: " + e.getMessage());
            return null;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
    
    // Método para atualizar uma reserva existente
    @Override
    public void update(Reserva reserva, Reserva nova) {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            reserva = entityManager.merge(reserva); // Mescla a reserva atualizada
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("Erro ao atualizar reserva: " + e.getMessage());
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    // Método para excluir uma reserva
    @Override
    public boolean delete(Reserva reserva) {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            Reserva r = entityManager.find(Reserva.class, reserva.getId());
            if (r != null) {
                entityManager.getTransaction().begin();
                entityManager.remove(r); // Remove a reserva do banco de dados
                entityManager.getTransaction().commit();
                return true;
            }
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("Erro ao excluir reserva: " + e.getMessage());
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return false;
    }

}