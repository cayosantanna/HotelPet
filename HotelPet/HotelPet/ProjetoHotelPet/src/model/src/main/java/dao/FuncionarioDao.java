package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.exception.ConstraintViolationException;

import model.Funcionario;
import model.HistoricoRh;

public class FuncionarioDao {

    private EntityManager em;

    public FuncionarioDao(EntityManager em) {
        this.em = em;
    }

    public void create(Funcionario funcionario) throws Exception {
    try {
        if (findByCpf(funcionario.getCpf()) != null) {
            throw new Exception("Já existe um funcionário com este CPF.");
        }
        em.getTransaction().begin();
        em.persist(funcionario);
        em.getTransaction().commit();
    } catch (Exception e) {
        em.getTransaction().rollback();
        System.err.println("Erro ao criar funcionário: " + e.getMessage());
        throw new Exception("Erro ao criar funcionário. Verifique os dados e tente novamente.");
    }
}


    public List<String> getHistorico() {
        return em.createQuery("SELECT h.acao FROM HistoricoRh h ORDER BY h.dataHora DESC", String.class)
                .getResultList();
    }

    public void update(Funcionario funcionario) throws Exception {
        try {
            em.getTransaction().begin();
            em.merge(funcionario);
            em.getTransaction().commit();
        } catch (ConstraintViolationException e) {
            em.getTransaction().rollback();
            throw new Exception("Erro de restrição no banco de dados: " + e.getConstraintName());
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Erro ao atualizar funcionário: " + e.getMessage());
        }
    }

    public void registrarAcao(String cpfRh, String acao) {
        try {
            em.getTransaction().begin();

            // Criação de uma nova entrada no histórico
            HistoricoRh historico = new HistoricoRh();
            historico.setCpfRh(cpfRh);
            historico.setAcao(acao);

            // Persiste a ação no banco
            em.persist(historico);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public Funcionario findByCpf(String cpf) {
        TypedQuery<Funcionario> query = em.createQuery("SELECT f FROM Funcionario f WHERE f.cpf = :cpf", Funcionario.class);
        query.setParameter("cpf", cpf);
        return query.getResultStream().findFirst().orElse(null);
    }

    public List<Funcionario> findAll() {
        TypedQuery<Funcionario> query = em.createQuery("SELECT f FROM Funcionario f", Funcionario.class);
        return query.getResultList();
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
        // Filtra o histórico com base nos funcionários
        String jpql = "SELECT h.acao FROM HistoricoRh h WHERE h.funcionario IN :funcionarios ORDER BY h.dataHora DESC";
        TypedQuery<String> query = em.createQuery(jpql, String.class);
        query.setParameter("funcionarios", funcionarios);
        return query.getResultList();
    }

    public Funcionario findByEmail(String email) {
        TypedQuery<Funcionario> query = em.createQuery("SELECT f FROM Funcionario f WHERE f.email = :email", Funcionario.class);
        query.setParameter("email", email);
        return query.getResultStream().findFirst().orElse(null);
    }

}
