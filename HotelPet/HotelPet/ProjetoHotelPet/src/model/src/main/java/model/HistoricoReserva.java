/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author neidi
 */
public class HistoricoReserva {
    private String nomePet;
    private String cpf;
    
    public HistoricoReserva(){

        this.nomePet = "";
        this.cpf = "000.000.000-00";
    }
    
    public void copiar(HistoricoReserva outro){
        this.nomePet = outro.getNomePet();
        this.cpf = outro.getCpf();
    }
    
     public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
     public String getNomePet() {
        return nomePet;
    }

    public void setNomePet(String nomePet) {
        this.nomePet = nomePet;
    }
}
