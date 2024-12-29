package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Cliente;
import factory.Persistencia;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import utils.EntityManagerUtil;

public class ClienteDAO implements IDao<Cliente> {

    private final String tabela = "Cliente";

    private String sql = "";

    @Override
    public List<Cliente> findAll() {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();

        try {
            TypedQuery<Cliente> query = entityManager.createNamedQuery("Cliente.findAll", Cliente.class);
            return query.getResultList();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("Erro: " + e.getMessage());
            return null;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public List<Cliente> findAll(String nome, String cpf) {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();

        try {
            TypedQuery<Cliente> query = entityManager.createNamedQuery("Cliente.findAllByNameCpf", Cliente.class);
            if (nome != null && !nome.trim().isEmpty()) {
                query.setParameter("nome", "%" + nome + "%");
            } else {
                query.setParameter("nome", null);

            }

            if (cpf != null && !cpf.trim().isEmpty()) {
                query.setParameter("cpf", "%" + cpf + "%");
            } else {
                query.setParameter("cpf", null);

            }

            return query.getResultList();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("Erro: " + e.getMessage());
            return null;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public Cliente find(Cliente obj) {
        this.sql = "SELECT * FROM " + this.tabela + " WHERE id = ?";
        try (Connection connection = Persistencia.getConnection(); PreparedStatement statement = connection.prepareStatement(this.sql)) {

            statement.setInt(1, obj.getId());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Cliente(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("cpf"),
                        resultSet.getString("email"),
                        resultSet.getString("telefone"),
                        resultSet.getString("endereco"),
                        resultSet.getString("cep"),
                        resultSet.getString("senha")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Cliente cliente) {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(cliente);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

    }

    @Override
    public void update(Cliente cliente) {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(cliente);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public boolean delete(Cliente obj) {
        this.sql = "DELETE FROM " + this.tabela + " WHERE id = ?";
        try (Connection connection = Persistencia.getConnection(); PreparedStatement statement = connection.prepareStatement(this.sql)) {

            statement.setInt(1, obj.getId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Cliente findByCPF(String cpf) {
        return findAll().stream()
                .filter(cliente -> cliente.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);
    }

    public Cliente findById(Integer id) {
         EntityManager entityManager = EntityManagerUtil.getEntityManager();

        try {
            TypedQuery<Cliente> query = entityManager.createNamedQuery("Cliente.findById", Cliente.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("Erro: " + e.getMessage());
            return null;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}
