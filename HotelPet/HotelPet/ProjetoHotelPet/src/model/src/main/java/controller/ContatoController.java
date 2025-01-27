/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author famil
 */
public class ContatoController {
    private dao.ContatoDao dao = new dao.ContatoDao();

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
}
