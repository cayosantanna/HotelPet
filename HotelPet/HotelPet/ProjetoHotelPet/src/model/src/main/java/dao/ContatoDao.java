/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import model.Funcionario;

/**
 *
 * @author famil
 */
public class ContatoDao {
    private EntityManager em;
    private static final java.util.List<model.Contato> listaContatos = new java.util.ArrayList<>();
    private static final int MENSAGENS_POR_HORA = 3;

    public ContatoDao(EntityManager em) {
        this.em = em;
    }

    public boolean podeEnviar(model.Contato c) {
        java.time.LocalDateTime agora = java.time.LocalDateTime.now();
        long qtd = listaContatos.stream()
            .filter(ct -> ct.getEmail().equalsIgnoreCase(c.getEmail()))
            .filter(ct -> ct.getDataEnvio().isAfter(agora.minusMinutes(60))) // Verifica mensagens nos últimos 60 minutos
            .count();
        return qtd < MENSAGENS_POR_HORA;
    }

    public void inserir(model.Contato c) {
        listaContatos.add(c);
    }

    public java.util.List<model.Contato> listar() {
        return new java.util.ArrayList<>(listaContatos); // Retorna uma cópia da lista para evitar modificações externas
    }

    public java.util.List<model.Contato> buscarPorEmail(String email) {
        return listaContatos.stream()
            .filter(c -> c.getEmail().equalsIgnoreCase(email))
            .collect(java.util.stream.Collectors.toList());
    }

    public Funcionario findFuncionarioByEmail(String email) {
        try {
            TypedQuery<Funcionario> query = em.createQuery(
                "SELECT f FROM Funcionario f WHERE f.email = :email", Funcionario.class
            );
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
