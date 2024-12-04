/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author neidi
 */
public class Funcionario extends Pessoa {
    private String cargo;
    
    public Funcionario(){
        super();
        this.cargo = "";
    }
    
    public Funcionario(int id, String nome, String cpf, String email, String telefone, String endereco, String cep, String senha) {
        super(id, nome, cpf, email, telefone, endereco, cep, senha);
        this.cargo = cargo;
    }
    
    public void copiar(Funcionario outro){
        this.id = outro.getId();
        this.nome = outro.getNome();
        this.cpf = outro.getCpf();
        this.email = outro.getEmail();
        this.telefone = outro.getTelefone();
        this.endereco = outro.getEndereco(); 
        this.cep = outro.getCep();
        this.senha = outro.getSenha();
    }
    
    @Override
    public String toString() {
        String txt = "---- Dados do aluno ------\n"
        + super.toString()
        +" Cargo: "+ this.getCargo()+ "\n"
        +"-------------------------------------\n";    
        
        return txt;
    }     

    /**
     * @return the cargo
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
    
}
