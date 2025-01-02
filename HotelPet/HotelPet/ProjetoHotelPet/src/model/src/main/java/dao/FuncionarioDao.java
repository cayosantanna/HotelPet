package dao;

import model.Funcionario;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class FuncionarioDao {

    private EntityManager em;

    public FuncionarioDao(EntityManager em) {
        this.em = em;
    }

    public void create(Funcionario funcionario) {
        em.persist(funcionario);
    }

    public void update(Funcionario funcionario) {
        em.merge(funcionario);
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

    public List<String> getHistorico() {
        return em.createQuery("SELECT h.acao FROM HistoricoRh h ORDER BY h.dataHora DESC", String.class)
                .getResultList();
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

    public void registrarAcao(String cpfRh, String acao) {
        String sql = "INSERT INTO historico_rh (cpf_rh, acao, data_hora) VALUES (:cpf, :acao, CURRENT_TIMESTAMP)";
        em.createNativeQuery(sql)
            .setParameter("cpf", cpfRh)
            .setParameter("acao", acao)
            .executeUpdate();
    }
    
}
