package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import factory.Persistencia;
import model.Cliente;

public class ClienteDAO implements IDao<Cliente> {

    @Override
    public List<Cliente> findAll() {
        EntityManager em = Persistencia.getEntityManager();
        try {
            TypedQuery<Cliente> query = em.createNamedQuery("Cliente.findAll", Cliente.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Cliente> findAll(String nome, String cpf) {
        EntityManager em = Persistencia.getEntityManager();
        try {
            TypedQuery<Cliente> query = em.createNamedQuery("Cliente.findAllByNameCpf", Cliente.class);
            query.setParameter("nome", nome != null && !nome.trim().isEmpty() ? "%" + nome + "%" : null);
            query.setParameter("cpf", cpf != null && !cpf.trim().isEmpty() ? "%" + cpf + "%" : null);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Cliente find(Cliente obj) {
        EntityManager em = Persistencia.getEntityManager();
        try {
            return em.find(Cliente.class, obj.getId());
        } finally {
            em.close();
        }
    }

    public Cliente findById(Integer id) {
        EntityManager em = Persistencia.getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close(); 
        }
    }

    @Override
    public void save(Cliente cliente) {
        EntityManager em = Persistencia.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Cliente cliente, Cliente novo) {
        EntityManager em = Persistencia.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(novo);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean delete(Cliente obj) {
        EntityManager em = Persistencia.getEntityManager();
        try {
            em.getTransaction().begin();
            Cliente cliente = em.find(Cliente.class, obj.getId());
            if (cliente != null) {
                em.remove(cliente);
                em.getTransaction().commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public Cliente findByCPF(String cpf) {
        EntityManager em = Persistencia.getEntityManager();
        try {
            TypedQuery<Cliente> query = em.createNamedQuery("Cliente.findByCpf", Cliente.class);
            query.setParameter("cpf", cpf);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Cliente findByCPF(String cpf, Integer ignoreId) {
        EntityManager em = Persistencia.getEntityManager();
        try {
            TypedQuery<Cliente> query = em.createNamedQuery("Cliente.findByCpfIgnoringId", Cliente.class);
            query.setParameter("cpf", cpf);
            query.setParameter("ignoreId", ignoreId);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Cliente findByEmail(String email) {
        EntityManager em = Persistencia.getEntityManager();
        try {
            TypedQuery<Cliente> query = em.createNamedQuery("Cliente.findByEmail", Cliente.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (javax.persistence.NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Cliente findByEmail(String email, Integer ignoreId) {
        EntityManager em = Persistencia.getEntityManager();
        try {
            TypedQuery<Cliente> query = em.createNamedQuery("Cliente.findByEmailIgnoringId", Cliente.class);
            query.setParameter("email", email);
            query.setParameter("ignoreId", ignoreId);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}

