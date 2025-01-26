/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author famil
 */
public class Contato {
    private String email;
    private String mensagem;
    private java.time.LocalDateTime dataEnvio;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public java.time.LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(java.time.LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }
}
