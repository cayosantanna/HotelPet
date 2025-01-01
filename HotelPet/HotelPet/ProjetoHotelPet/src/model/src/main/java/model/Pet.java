/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import lombok.Data;

@Data
@Entity
@NamedQuery(name = "Pet.findByClienteId", query = "SELECT p FROM Pet p WHERE p.cliente.id = :clienteId AND p.Status = true")
@NamedQuery(name = "Pet.findByClienteIdFilteredByName", query = "SELECT p FROM Pet p WHERE p.cliente.id = :clienteId AND p.Status = true AND (:nome IS NULL OR p.nome LIKE :nome)")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String datanascimento;
    private String especie;
    private String raca;
    private String porte;
    private String sexo;
    private String caracteristicasFisicas;
    private String historicoDoencas;
    private String medicacoes;
    private Boolean Status;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Pet() {

    }

    public Pet(
            int id,
            String nome,
            String datanascimento,
            String especie,
            String raca,
            String porte,
            String sexo,
            String caracteristicasFisicas,
            String historicoDoencas,
            String medicacoes,
            Boolean Status
    ) {
        this.id = id;
        this.nome = nome;
        this.datanascimento = datanascimento;
        this.especie = especie;
        this.raca = raca;
        this.porte = porte;
        this.sexo = sexo;
        this.caracteristicasFisicas = caracteristicasFisicas;
        this.historicoDoencas = historicoDoencas;
        this.medicacoes = medicacoes;
        this.Status = true;
    }

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

    public String getDatanascimento() {
        return datanascimento;
    }

    public void setDatanascimento(String datanascimento) {
        this.datanascimento = datanascimento;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCaracteristicasFisicas() {
        return caracteristicasFisicas;
    }

    public void setCaracteristicasFisicas(String caracteristicasFisicas) {
        this.caracteristicasFisicas = caracteristicasFisicas;
    }

    public String getHistoricoDoencas() {
        return historicoDoencas;
    }

    public void setHistoricoDoencas(String historicoDoencas) {
        this.historicoDoencas = historicoDoencas;
    }

    public String getMedicacoes() {
        return medicacoes;
    }

    public void setMedicacoes(String medicacoes) {
        this.medicacoes = medicacoes;
    }
    
    public Cliente getCliente(){
        return this.cliente;
    }
    
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }

    /**
     * @return the Status
     */
    public Boolean getStatus() {
        return Status;
    }

    /**
     * @param Status the Status to set
     */
    public void setStatus(Boolean Status) {
        this.Status = Status;
    }
}
