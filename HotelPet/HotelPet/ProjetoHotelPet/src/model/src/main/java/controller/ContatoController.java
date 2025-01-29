/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Funcionario;

/**
 *
 * @author famil
 */
public class ContatoController {
    private final dao.ContatoDao dao;
    
    public ContatoController() {
        javax.persistence.EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("exemplo-jpa");
        javax.persistence.EntityManager em = emf.createEntityManager();
        this.dao = new dao.ContatoDao(em);
    }

    public String adicionarContato(model.Contato c) {
        if (c == null) {
            return "Contato inválido.";
        }
        String email = c.getEmail();
        if (email == null || !email.contains("@")) {
            return "Email inválido.";
        }
        c.setDataEnvio(java.time.LocalDateTime.now());
        if (!dao.podeEnviar(c)) {
            return "Limite de mensagens por hora excedido para este email.";
        }
        try {
            dao.inserir(c);
            return "Mensagem enviada com sucesso!";
        } catch (Exception e) {
            return "Falha ao enviar a mensagem.";
        }
    }

    public java.util.List<model.Contato> listarContatos() {
        return dao.listar();
    }

    public java.util.List<model.Contato> buscarPorEmail(String email) {
        return dao.buscarPorEmail(email);
    }

    public boolean isGestorRH(String email) {
        Funcionario func = dao.findFuncionarioByEmail(email);
        if (func != null) {
            String cargo = func.getCargo().toLowerCase();
            return cargo.contains("RH") || 
                   cargo.contains("gestor") || 
                   cargo.equalsIgnoreCase("Gestor de RH");
        }
        return false;
    }
}
