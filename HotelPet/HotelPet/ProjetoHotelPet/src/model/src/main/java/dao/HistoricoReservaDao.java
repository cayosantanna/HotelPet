/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.HistoricoReserva;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author neidi
 */
public class HistoricoReservaDao {
    
    private EntityManager em;


    public HistoricoReservaDao(EntityManager em) {
        this.em = em;
    }
  

    public List<HistoricoReserva> findAll() {
        try {
            em.clear();
            return em.createQuery("SELECT r FROM HistoricoReserva r ORDER BY r.dataReserva DESC", HistoricoReserva.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public HistoricoReserva findById(Long id) {
        try {
            return em.find(HistoricoReserva.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void update(HistoricoReserva reserva) throws Exception {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            reserva = em.merge(reserva);
            em.flush();
            tx.commit();
            em.clear();
            System.out.println("Histórico de reserva atualizado com sucesso: Pet " + reserva.getNomePet());
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }
    
//nao esta sendo usado no momento
    public void delete(Long id) throws Exception {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            HistoricoReserva reserva = findById(id);
            if (reserva != null) {
                em.remove(reserva);
            }
            em.flush();
            tx.commit();
            em.clear();
            System.out.println("Histórico de reserva deletado com sucesso: Pet " + reserva.getNomePet());
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    public List<HistoricoReserva> findByCpfOrPetName(String cpf, String petName) {
        try {
            String jpql = "SELECT r FROM HistoricoReserva r WHERE r.cpfCliente = :cpf OR r.nomePet LIKE :petName";
            TypedQuery<HistoricoReserva> query = em.createQuery(jpql, HistoricoReserva.class);
            query.setParameter("cpf", cpf);
            query.setParameter("petName", "%" + petName + "%");
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<String> getHistorico() {
        try {
            em.clear();
            String jpql = "SELECT CONCAT('Reserva para o pet ', r.nomePet, ' (CPF: ', r.cpfCliente, ') em ', " +
                          "function('DATE_FORMAT', r.dataReserva, '%d/%m/%Y %H:%i')) " +
                          "FROM HistoricoReserva r ORDER BY r.dataReserva DESC";
            TypedQuery<String> query = em.createQuery(jpql, String.class);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao buscar histórico de reservas: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public long countReservasPorPet(String nomePet) {
        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(r) FROM HistoricoReserva r WHERE r.nomePet = :nomePet", Long.class);
            query.setParameter("nomePet", nomePet);
            return query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Erro ao contar reservas por pet: " + e.getMessage());
            return 0;
        }
    }

    public long countReservasPorCliente(String cpfCliente) {
        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(r) FROM HistoricoReserva r WHERE r.cpfCliente = :cpfCliente", Long.class);
            query.setParameter("cpfCliente", cpfCliente);
            return query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Erro ao contar reservas por cliente: " + e.getMessage());
            return 0;
        }
    }

   


    
}

