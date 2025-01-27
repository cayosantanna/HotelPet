package controller;


import util.JPAUtil; 

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.TypedQuery;
import model.Funcionario;

public class FuncionarioRhController {
    
    EntityManager em = JPAUtil.getEntityManager();

    public List<String> getHistorico() {
    List<String> historico = em.createQuery("SELECT h.acao FROM HistoricoRh h ORDER BY h.dataHora DESC", String.class)
                               .getResultList();
    if (historico.isEmpty()) {
        return List.of("Nenhum histórico encontrado.");
    }
    return historico;
}
    
    public List<Funcionario> findByNomeOuCpf(String nome, String cpf) {
    String jpql = "SELECT f FROM Funcionario f WHERE (:nome IS NULL OR f.nome LIKE :nome) AND (:cpf IS NULL OR f.cpf = :cpf)";
    TypedQuery<Funcionario> query = em.createQuery(jpql, Funcionario.class);
    query.setParameter("nome", nome != null ? "%" + nome + "%" : null);
    query.setParameter("cpf", cpf != null ? cpf : null);
    return query.getResultList();
}

}
