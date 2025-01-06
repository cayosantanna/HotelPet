package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import model.Funcionario;
import model.HistoricoRh;

public class FuncionarioDao {

    private EntityManager em;

    public FuncionarioDao(EntityManager em) {
        this.em = em;
    }

    public void create(Funcionario funcionario) throws Exception {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(funcionario);
            em.flush();
            tx.commit();
            em.clear();
            System.out.println("Funcionário criado com sucesso: " + funcionario.getNome());
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            Funcionario check = findByCpf(funcionario.getCpf());
            if (check != null) {
                System.out.println("Funcionário já existe no banco");
                return;
            }
            throw e;
        }
    }

    public List<String> getHistorico() {
        try {
            em.clear();
            String jpql = "SELECT CONCAT('Gerente RH (', f.nome, ' - CPF: ', h.cpfRh, ') ', h.acao, ' em ', " +
                         "DATE_FORMAT(h.dataHora, '%d/%m/%Y %H:%i')) " +
                         "FROM HistoricoRh h " +
                         "JOIN Funcionario f ON f.cpf = h.cpfRh " +
                         "ORDER BY h.dataHora DESC";
            
            TypedQuery<String> query = em.createQuery(jpql, String.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void update(Funcionario funcionario) throws Exception {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            if (!funcionario.isAtivo()) {
                String timestamp = String.valueOf(System.currentTimeMillis());
                funcionario.setEmail("inativo." + timestamp + "." + funcionario.getEmail());
                funcionario.setTelefone("ex." + timestamp + "." + funcionario.getTelefone());
            }
            funcionario = em.merge(funcionario);
            em.flush();
            tx.commit();
            em.clear();
            System.out.println("Funcionário atualizado com sucesso: " + funcionario.getNome());
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    public void registrarAcao(String cpfRh, String acao) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            
            // Busca o funcionário para verificar o cargo
            Funcionario funcionario = findByCpf(cpfRh);
            String cargoPrefix = funcionario.getCargo().equalsIgnoreCase("Gestor de RH") ? 
                               "RH" : "Recepcionista";
            
            HistoricoRh historico = new HistoricoRh();
            historico.setCpfRh(cpfRh);
            historico.setAcao(cargoPrefix + " - " + acao);
            historico.setDataHora(new Date());
            
            em.persist(historico);
            em.flush();
            tx.commit();
            em.clear();
            
            System.out.println("Ação registrada por " + cargoPrefix + ": " + acao);
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    public Funcionario findByCpf(String cpf) {
        TypedQuery<Funcionario> query = em.createQuery("SELECT f FROM Funcionario f WHERE f.cpf = :cpf", Funcionario.class);
        query.setParameter("cpf", cpf);
        return query.getResultStream().findFirst().orElse(null);
    }

    public List<Funcionario> findAll() {
        try {
            em.clear(); // Limpa o cache antes de buscar
            return em.createQuery(
                "SELECT f FROM Funcionario f WHERE f.ativo = true ORDER BY f.nome", 
                Funcionario.class
            ).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Funcionario> findByNomeOuCpf(String nome, String cpf) {
        String jpql = "SELECT f FROM Funcionario f WHERE f.nome LIKE :nome OR f.cpf = :cpf";
        TypedQuery<Funcionario> query = em.createQuery(jpql, Funcionario.class);
        query.setParameter("nome", "%" + nome + "%");
        query.setParameter("cpf", cpf);
        return query.getResultList();
    }

    // Método para obter histórico filtrado com base nos funcionários
    public List<String> getHistorico(List<Funcionario> funcionarios) {
        try {
            TypedQuery<String> query = em.createQuery(
                "SELECT CONCAT('RH (CPF: ', h.cpfRh, ') ', h.acao, ' em ', function('DATE_FORMAT', h.dataHora, '%d/%m/%Y %H:%i')) " +
                "FROM HistoricoRh h WHERE h.cpfRh IN :cpfs ORDER BY h.dataHora DESC", String.class);
            List<String> cpfs = funcionarios.stream().map(Funcionario::getCpf).collect(Collectors.toList());
            query.setParameter("cpfs", cpfs);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao buscar histórico filtrado: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public Funcionario findByEmail(String email) {
        try {
            TypedQuery<Funcionario> query = em.createQuery(
                "SELECT f FROM Funcionario f WHERE f.email = :email AND f.ativo = true", 
                Funcionario.class
            );
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public long countRhAtivos() {
        try {
            TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(f) FROM Funcionario f WHERE f.cargo = :cargo AND f.ativo = true",
                Long.class
            );
            query.setParameter("cargo", "Gestor de RH");
            return query.getSingleResult();
        } catch (NoResultException e) {
            return 0;
        } catch (Exception e) {
            System.out.println("Erro ao contar RH ativos: " + e.getMessage());
            return 0;
        }
    }

}
