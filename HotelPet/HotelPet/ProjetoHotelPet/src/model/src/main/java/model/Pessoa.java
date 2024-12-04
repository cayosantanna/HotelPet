
package model;

/**
 *
 * @author neidi
 */
public class Pessoa {
    
    protected int id;
    protected String nome;
    protected String cpf;
    protected String email;
    protected String telefone;
    protected String endereco;
    protected String cep;
    protected String senha;
    
    public Pessoa() {
        this.id = 0;
        this.nome = "";
        this.cpf = "";
        this.email = "";
        this.telefone = "";
        this.endereco = "";
        this.cep = "";
        this.senha = "";
    }

    public Pessoa(int id, String nome, String cpf, String email, String telefone, String endereco, String cep, String senha) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cep = cep;
        this.senha = senha;
    }
    
    @Override
    public String toString() {
        String txt ="Id: " + this.id +"\n"+ 
        "Nome: " + this.nome +"\n"
        +"Cpf: "+ this.cpf + "\n"
        +"Email: "+ this.email+"\n"
        +"Telefone: "+ this.telefone+"\n"
        +"Endereco: "+ this.endereco+"\n"
        +"Cep: "+ this.cep+"\n"
        +"Senha: "+ this.email+"\n";
        return txt;
    }  
    
    @Override
    public boolean equals(Object obj) {
    // Verifica se o objeto é da mesma classe
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;

    // Faz o cast para a classe Pessoa
    Pessoa outro = (Pessoa) obj;

    // Compara atributos relevantes
    if (this.id != outro.id) 
        return false;
    if (!this.nome.equals(outro.nome)) 
        return false;
    if (!this.cpf.equals(outro.cpf)) 
        return false;

    return true; // Se todos os atributos forem iguais
}

    
    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * @return the endereco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * @param cep the cep to set
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    
}
