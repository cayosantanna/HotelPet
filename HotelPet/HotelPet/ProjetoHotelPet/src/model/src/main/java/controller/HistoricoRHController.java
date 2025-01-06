/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.HistoricoRhDAO;
import model.Funcionario;

import javax.persistence.TypedQuery;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;

import model.Funcionario;
import model.HistoricoRh;
import util.JPAUtil;


public class HistoricoRHController {
    private final HistoricoRhDAO historicoRhDAO;
    private final EntityManager em;

    public HistoricoRHController() {
        // Inicializa o EntityManager e o DAO
        this.em = JPAUtil.getEntityManager();
        this.historicoRhDAO = new HistoricoRhDAO((jakarta.persistence.EntityManager) em);
    }

    /**
     * Obtém todo o histórico de RH.
     * @return Lista formatada de strings representando o histórico.
     */
    public List<String> getHistoricoRH() {
        List<HistoricoRh> historicoList = historicoRhDAO.findAll();
        if (historicoList.isEmpty()) {
            return List.of("Nenhum histórico encontrado.");
        }
        return historicoList.stream()
                .map(h -> String.format("CPF: %s | Ação: %s | Data/Hora: %s",
                        h.getCpfRh(), h.getAcao(), h.getDataHora()))
                .collect(Collectors.toList());
    }

    /**
     * Filtra o histórico de RH com base no nome ou CPF.
     * @param nome Nome do funcionário (pode ser parcial).
     * @param cpf CPF do funcionário.
     * @return Lista formatada de strings representando o histórico filtrado.
     */
    public List<String> filtrarHistorico(String nome, String cpf) {
        String jpql = "SELECT h FROM HistoricoRh h WHERE " +
                      "(:cpf IS NULL OR h.cpfRh = :cpf)";
        TypedQuery<HistoricoRh> query = em.createQuery(jpql, HistoricoRh.class);
        query.setParameter("cpf", cpf != null && !cpf.isEmpty() ? cpf : null);

        List<HistoricoRh> historicoList = query.getResultList();
        if (historicoList.isEmpty()) {
            return List.of("Nenhum histórico correspondente encontrado.");
        }
        return historicoList.stream()
                .map(h -> String.format("CPF: %s | Ação: %s | Data/Hora: %s",
                        h.getCpfRh(), h.getAcao(), h.getDataHora()))
                .collect(Collectors.toList());
    }

    /**
     * Registra uma nova ação no histórico de RH.
     * @param cpfRh CPF do RH que realizou a ação.
     * @param acao Descrição da ação.
     */
    public void registrarAcao(String cpfRh, String acao) {
        HistoricoRh historico = new HistoricoRh();
        historico.setCpfRh(cpfRh);
        historico.setAcao(acao);
        historico.setDataHora(new java.util.Date());
        historicoRhDAO.save(historico);
    }

    /**
     * Busca funcionários pelo nome ou CPF.
     * @param nome Nome do funcionário (parcial ou completo).
     * @param cpf CPF do funcionário.
     * @return Lista de funcionários correspondentes.
     */
    public List<Funcionario> findByNomeOuCpf(String nome, String cpf) {
        String jpql = "SELECT f FROM Funcionario f WHERE " +
                      "(:nome IS NULL OR f.nome LIKE :nome) AND " +
                      "(:cpf IS NULL OR f.cpf = :cpf)";
        TypedQuery<Funcionario> query = em.createQuery(jpql, Funcionario.class);
        query.setParameter("nome", nome != null && !nome.isEmpty() ? "%" + nome + "%" : null);
        query.setParameter("cpf", cpf != null && !cpf.isEmpty() ? cpf : null);

        return query.getResultList();
    }
}

