package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.Cliente;
import org.hibernate.exception.ConstraintViolationException;
import utils.EntityManagerUtil;

public class ClienteDAO implements IDao<Cliente> {

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
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            return entityManager.find(Cliente.class, obj.getId());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public Cliente findById(Integer id) {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            return entityManager.find(Cliente.class, id);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
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
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public void update(Cliente cliente, Cliente novo) {
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

    public void update(Cliente novo) {
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
    public boolean delete(Cliente obj) {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            Cliente cliente = find(obj);
            if (cliente != null) {
                entityManager.getTransaction().begin();
                entityManager.remove(cliente);
                entityManager.getTransaction().commit();
                return true;
            }
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return false;
    }

    public Cliente findByCPF(String cpf) {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            TypedQuery<Cliente> query = entityManager.createNamedQuery("Cliente.findByCpf", Cliente.class);
            query.setParameter("cpf", cpf);
            return query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public Cliente findByCPF(String cpf, Integer ignoreId) {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            TypedQuery<Cliente> query = entityManager.createNamedQuery("Cliente.findByCpfIgnoringId", Cliente.class);
            query.setParameter("cpf", cpf);
            query.setParameter("ignoreId", ignoreId);

            return query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public Cliente findByEmail(String email) {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            TypedQuery<Cliente> query = entityManager.createNamedQuery("Cliente.findByEmail", Cliente.class);
            query.setParameter("email", email);

            return query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public Cliente findByEmail(String email, Integer ignoreId) {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            TypedQuery<Cliente> query = entityManager.createNamedQuery("Cliente.findByEmailIgnoringId", Cliente.class);
            query.setParameter("email", email);
            query.setParameter("ignoreId", ignoreId);

            return query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}
