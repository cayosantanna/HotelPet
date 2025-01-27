/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
@Entity
@NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c WHERE c.funcionario = 0")
@NamedQuery(name = "Cliente.findAllByNameCpf", query = "SELECT c FROM Cliente c WHERE (:nome IS NULL OR c.nome LIKE :nome) AND (:cpf IS NULL OR c.cpf LIKE :cpf) AND c.funcionario = 0")
@NamedQuery(name = "Cliente.findById", query = "SELECT c FROM Cliente c WHERE c.id = :id")
@NamedQuery(name = "Cliente.findByCpf", query = "SELECT c FROM Cliente c WHERE c.cpf = :cpf")
@NamedQuery(name = "Cliente.findByCpfIgnoringId", query = "SELECT c FROM Cliente c WHERE c.cpf = :cpf AND c.id != :ignoreId")
@NamedQuery(name = "Cliente.findByEmail", query = "SELECT c FROM Cliente c WHERE c.email = :email")
@NamedQuery(name = "Cliente.findByEmailIgnoringId", query = "SELECT c FROM Cliente c WHERE c.email = :email AND c.id != :ignoreId")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    @Column(unique = true)
    private String cpf;
    @Column(unique = true)
    private String email;
    private String telefone;
    private String endereco;
    private String cep;
    @Column(nullable = false)
    private String senha;
    @Transient // Não será persistido no banco
    private String senhaOriginal;

    private Boolean funcionario = false;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Pet> pet;

    public Cliente() {
    }

    public Cliente(int id, String nome, String cpf, String email, String telefone, String endereco, String cep, String senha) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf.replaceAll("[^\\d]", "");
        this.email = email;
        this.telefone = telefone.replaceAll("[^\\d]", "");
        this.endereco = endereco;
        this.cep = (cep != null) ? cep.replaceAll("[^\\d]", "") : null;
        if (!(senha.isBlank() || senha.isEmpty())) {
            this.senhaOriginal = senha; // Guarda a senha original
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            this.senha = encoder.encode(senha); // Guarda a senha criptografada
        }

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
        this.cpf = cpf.replaceAll("[^\\d]", "");
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
        this.telefone = telefone.replaceAll("[^\\d]", "");
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
        this.cep = cep.replaceAll("[^\\d]", "");
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return this.senhaOriginal != null ? this.senhaOriginal : this.senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senhaOriginal = senha;
        if (!senha.isEmpty()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            this.senha = encoder.encode(senha);
        }
    }
    
    public void setHashedSenha(String hashedSenha){
        this.senha = hashedSenha;
        this.senhaOriginal = hashedSenha; // Guarda também como senha original
    }
    
    @Override
    public String toString() {
        return "Cliente{id=" + getId() + ", nome='" + getNome() + "', cpf='" + getCpf() + "', email='" + getEmail() + "', telefone='" + getTelefone() + "'}";
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    public Boolean getFuncionario() {
        return this.funcionario;
    }

    public void setFuncionario(Boolean funcionario) {
        this.funcionario = funcionario;
    }

}
