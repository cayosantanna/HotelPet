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

    public boolean adicionarContato(model.Contato c) {
        if (c == null) {
            return false;
        }
        String email = c.getEmail();
        if (email == null || !email.contains("@")) {
            return false;
        }
        c.setDataEnvio(java.time.LocalDateTime.now());
        if (!dao.podeEnviar(c)) {
            return false;
        }
        dao.inserir(c);
        return true;
    }

    public java.util.List<model.Contato> listarContatos() {
        return dao.listar();
    }
}
