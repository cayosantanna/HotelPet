/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author famil
 */
public class ContatoDao {
    private static final java.util.List<model.Contato> listaContatos = new java.util.ArrayList<>();
    private static final int MENSAGENS_POR_HORA = 3;

    public boolean podeEnviar(model.Contato c) {
        java.time.LocalDateTime agora = java.time.LocalDateTime.now();
        long qtd = listaContatos.stream()
            .filter(ct -> ct.getEmail().equalsIgnoreCase(c.getEmail()))
            .filter(ct -> java.time.Duration.between(ct.getDataEnvio(), agora).toHours() < 1)
            .count();
        return qtd < MENSAGENS_POR_HORA;
    }

    public void inserir(model.Contato c) {
        listaContatos.add(c);
    }

    public java.util.List<model.Contato> listar() {
        return listaContatos;
    }
}
