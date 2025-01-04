package dao;

import model.Pet;
import factory.Persistencia;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import util.EntityManagerUtil;

public class PetDAO implements IDao<Pet> {

    private final String tabela = "pets";
    private String sql = "";

    @Override
    public List<Pet> findAll() {
        List<Pet> pets = new ArrayList<>();
        EntityManager entityManager = Persistencia.getEntityManager();

        try {
            pets = entityManager.createQuery("SELECT p FROM Pet p", Pet.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return pets;
    }

    public List<Pet> findByResponsavel(String cpfResponsavel) {
        EntityManager entityManager = Persistencia.getEntityManager();
        List<Pet> pets = new ArrayList<>();

        try {
            pets = entityManager.createQuery("SELECT p FROM Pet p WHERE p.cliente.cpf = :cpfResponsavel", Pet.class)
                    .setParameter("cpfResponsavel", cpfResponsavel)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return pets;
    }

    @Override
    public void save(Pet pet) {
        EntityManager entityManager = Persistencia.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            // Verifica se já existe um pet com o mesmo nome, espécie e data de nascimento para o mesmo cliente
            List<Pet> petsExistentes = entityManager.createQuery("SELECT p FROM Pet p WHERE p.cliente.id = :clienteId AND p.nome = :nome AND p.especie = :especie AND p.datanascimento = :datanascimento", Pet.class)
                    .setParameter("clienteId", pet.getCliente().getId()) // A chave do cliente
                    .setParameter("nome", pet.getNome())
                    .setParameter("especie", pet.getEspecie())
                    .setParameter("datanascimento", pet.getDatanascimento())
                    .getResultList();

            if (!petsExistentes.isEmpty()) {
                throw new IllegalArgumentException("Erro: Já existe um pet com o mesmo nome, espécie e data de nascimento para esse cliente.");
            }

            // Se a validação passar, persiste o pet
            transaction.begin();
            entityManager.persist(pet);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
<<<<<<< HEAD
    public void update(Pet pet, Pet novo1) {
        this.sql = "UPDATE " + this.tabela + " SET nome = ?, cpfResponsavel = ?, especie = ?, raca = ?, porte = ? WHERE id = ?";

        try (Connection connection = Persistencia.getConnection(); PreparedStatement statement = connection.prepareStatement(this.sql)) {

            statement.setString(1, pet.getNome());
            statement.setString(2, pet.getCpfResponsavel());
            statement.setString(3, pet.getEspecie());
            statement.setString(4, pet.getRaca());
            statement.setString(5, pet.getPorte());
            statement.setInt(6, pet.getId());
            statement.setString(7, pet.getSexo());
            statement.setString(8, pet.getCaracteristicasFisicas());
            statement.setString(9, pet.getHistoricoDoencas());
            statement.setString(10, pet.getMedicacoes());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
=======
    public void update(Pet pet, Pet novo) {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(novo);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
>>>>>>> Main
        }
    }
    
    public void update(Pet novo) {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(novo);
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
    public boolean delete(Pet pet) {
        EntityManager entityManager = Persistencia.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Pet petToUpdate = entityManager.find(Pet.class, pet.getId());
            if (petToUpdate != null) {
                petToUpdate.setStatus(false);  // Marca o pet como inativo
            }
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return false;
    }

    @Override
    public Pet find(Pet pet) {
        EntityManager entityManager = Persistencia.getEntityManager();
        Pet foundPet = null;

        try {
            foundPet = entityManager.find(Pet.class, pet.getId());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return foundPet;
    }

    public List<Pet> findByClienteId(int clienteId) {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            TypedQuery<Pet> query = entityManager.createNamedQuery("Pet.findByClienteId", Pet.class);
            query.setParameter("clienteId", clienteId);
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

    public Pet findById(int id) {
        EntityManager entityManager = Persistencia.getEntityManager();
        Pet pet = null;

        try {
            pet = entityManager.find(Pet.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return pet;
    }
}
