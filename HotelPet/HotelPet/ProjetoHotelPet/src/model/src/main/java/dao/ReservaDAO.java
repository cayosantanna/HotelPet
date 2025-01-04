package dao;

<<<<<<< HEAD
import factory.Persistencia;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import model.Pet;
=======
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
>>>>>>> Main
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

<<<<<<< HEAD
    @Override
    public void update(Reserva obj, Pet novo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Reserva> buscarPorNomeOuCpf(String nomePet, String cpfResponsavel) throws ParseException {
    List<Reserva> reservas = new ArrayList<>();
    StringBuilder sql = new StringBuilder("SELECT * FROM reservas r ");
    sql.append("JOIN pets p ON r.id_pet = p.id_pet ");
    sql.append("JOIN responsaveis resp ON r.id_responsavel = resp.id_responsavel ");
    sql.append("WHERE 1=1 ");
    
    // Adicionar condições baseadas nos parâmetros fornecidos
    if (nomePet != null && !nomePet.isEmpty()) {
        sql.append("AND p.nome LIKE ? ");
    }
    if (cpfResponsavel != null && !cpfResponsavel.isEmpty()) {
        sql.append("AND resp.cpf = ? ");
    }

    try (PreparedStatement stmt = connection.prepareStatement(sql.toString())) {
        int index = 1;
        
        // Preencher os parâmetros da consulta
        if (nomePet != null && !nomePet.isEmpty()) {
            stmt.setString(index++, "%" + nomePet + "%");
        }
        if (cpfResponsavel != null && !cpfResponsavel.isEmpty()) {
            stmt.setString(index++, cpfResponsavel);
        }
        
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Reserva reserva = new Reserva();
            reserva.setId(rs.getInt("id_reserva"));
            Pet pet = new Pet();
            pet.setNome(rs.getString("nome_pet"));
            reserva.setPet(pet);
            // Preencher os outros campos de Reserva conforme necessário
            reservas.add(reserva);
        }
    } catch (SQLException e) {
    }
    
    return reservas;
}

    
}

=======
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
>>>>>>> Main
