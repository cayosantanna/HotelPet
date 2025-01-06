package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"cpf"}),
    @UniqueConstraint(columnNames = {"email"}),
    @UniqueConstraint(columnNames = {"telefone"})
})
public class Funcionario extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String telefone;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String cargo; // "COMUM" ou "RH"

    @Column(nullable = false)
    private boolean ativo;

    private Date dataDesligamento;

    // Construtor padrão
    public Funcionario() {
        super();
        this.cargo = "COMUM";
        this.ativo = true;
        this.dataDesligamento = null;
    }

    // Construtor completo
    public Funcionario(int id, String nome, String cpf, String email, String telefone, String endereco, String cep, String senha, String cargo) {
        super(id, nome, cpf, email, telefone, endereco, cep, senha);
        this.cargo = cargo;
        this.ativo = true;
        this.dataDesligamento = null;
    }

    // Construtor simplificado
    public Funcionario(String nome, String cpf, String email, String telefone, String senha, String cargo, boolean ativo) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.cargo = cargo;
        this.ativo = ativo;
        this.dataDesligamento = null;
    }

    // Método para copiar dados de outro funcionário
    public void copiar(Funcionario outro) {
        this.setId(outro.getId());
        this.setNome(outro.getNome());
        this.setCpf(outro.getCpf());
        this.setEmail(outro.getEmail());
        this.setTelefone(outro.getTelefone());
        this.setEndereco(outro.getEndereco());
        this.setCep(outro.getCep());
        this.setSenha(outro.getSenha());
        this.setCargo(outro.getCargo());
        this.setAtivo(outro.isAtivo());
        this.setDataDesligamento(outro.getDataDesligamento());
    }

    // Adicionar método para facilitar a verificação do status
    public boolean podeLogar() {
        return this.ativo && this.dataDesligamento == null;
    }

    @Override
    public String toString() {
        String txt = "---- Dados do Funcionário ------\n" +
                     "Nome: " + this.getNome() + "\n" +
                     "CPF: " + this.getCpf() + "\n" +
                     "Email: " + this.getEmail() + "\n" +
                     "Telefone: " + this.getTelefone() + "\n" +
                     "Cargo: " + this.getCargo() + "\n" +
                     "Status: " + (this.isAtivo() ? "Ativo" : "Inativo") + "\n";

        if (!this.isAtivo() && this.getDataDesligamento() != null) {
            txt += "Data de Desligamento: " + this.getDataDesligamento() + "\n";
        }

        txt += "-------------------------------------\n";
        return txt;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Date getDataDesligamento() {
        return dataDesligamento;
    }

    public void setDataDesligamento(Date dataDesligamento) {
        this.dataDesligamento = dataDesligamento;
    }
}
