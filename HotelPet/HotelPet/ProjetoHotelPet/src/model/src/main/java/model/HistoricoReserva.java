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
    
     public HistoricoReserva(String nomePet,String cpf) {
        this.nomePet = nomePet;
        this.cpf = cpf;
    }       
    
    public void copiar(HistoricoReserva outro){
        this.nomePet = outro.getNomePet();
        this.cpf = outro.getCpf();
    }
    
     @Override
    public String toString() {
        String txt = "---- Dados do Cliente e Pet ------\n"
        + super.toString()
        +" NomePet: "+ this.nomePet+ "\n"
        +" CPF: "+ this.cpf + "\n"
        +"-------------------------------------\n";    
        
        return txt;
    }     
    
     public String cabecalho(){
        return "Nome;CPF;\n";
    }
     
     public String atributoToCSV(){
        String aux = this.nomePet + ";" + 
                this.cpf +";"+"\n";
        return aux;    
    }
     
      /**
     * Preenche os campos do objeto com uma linha CSV
     * @param csv 
     */
    public void CSVToAtributo(String csv){
        String vetor[] = csv.split(";");
        
        this.nomePet = vetor[0];
        this.cpf = vetor[1];

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
