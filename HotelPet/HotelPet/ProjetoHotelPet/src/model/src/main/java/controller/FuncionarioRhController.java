package controller;

import dao.HistoricoRhDAO;
import model.HistoricoRh;
import util.JPAUtil; 

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.TypedQuery;
import model.Funcionario;

public class FuncionarioRhController {
    private final HistoricoRhDAO historicoRHDao;
    EntityManager em = JPAUtil.getEntityManager();

    public FuncionarioRhController() {
        // Certifique-se de que o método getEntityManager() existe na classe JPAUtil
        this.historicoRHDao = new HistoricoRhDAO((javax.persistence.EntityManager) em);
    }

    // Retorna o histórico de RH formatado como lista de Strings
    public List<String> getHistoricoRH() {
        List<HistoricoRh> historicoList = historicoRHDao.findAll(); // Chamando método de instância
        return historicoList.stream()
                .map(h -> String.format("CPF: %s | Ação: %s | Data/Hora: %s",
                        h.getCpfRh(), h.getAcao(), h.getDataHora()))
                .collect(Collectors.toList());
    }
    
    public List<String> getHistorico() {
    List<String> historico = em.createQuery("SELECT h.acao FROM HistoricoRh h ORDER BY h.dataHora DESC", String.class)
                               .getResultList();
    if (historico.isEmpty()) {
        return List.of("Nenhum histórico encontrado.");
    }
    return historico;
}

    // Filtra histórico de RH com base no CPF
    public List<String> filtrarHistorico(String nome, String cpf) {
        List<HistoricoRh> historicoList = historicoRHDao.findAll(); // Use o DAO instanciado
        return historicoList.stream()
                .filter(h -> cpf.isEmpty() || h.getCpfRh().equals(cpf))
                .map(h -> String.format("CPF: %s | Ação: %s | Data/Hora: %s",
                        h.getCpfRh(), h.getAcao(), h.getDataHora()))
                .collect(Collectors.toList());
    }
    
    public List<Funcionario> findByNomeOuCpf(String nome, String cpf) {
    String jpql = "SELECT f FROM Funcionario f WHERE (:nome IS NULL OR f.nome LIKE :nome) AND (:cpf IS NULL OR f.cpf = :cpf)";
    TypedQuery<Funcionario> query = em.createQuery(jpql, Funcionario.class);
    query.setParameter("nome", nome != null ? "%" + nome + "%" : null);
    query.setParameter("cpf", cpf != null ? cpf : null);
    return query.getResultList();
}


    // Registra uma nova ação no histórico
    public void registrarAcao(String cpfRh, String acao) {
        HistoricoRh historico = new HistoricoRh();
        historico.setCpfRh(cpfRh);
        historico.setAcao(acao);
        historicoRHDao.save(historico);
    }
}
