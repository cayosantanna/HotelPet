/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Pet {
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
    private String cpfResponsavel;

    
    public Pet(int id, String nome, String datanascimento, String especie, String raca, String porte, String sexo, String caracteristicasFisicas,
               String historicoDoencas, String medicacoes, String cpfResponsavel) {
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
        this.cpfResponsavel = cpfResponsavel;
    }

    /**
     * @return the nome
     */
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
     * @return the especie
     */
    public String getEspecie() {
        return especie;
    }

    /**
     * @param especie the especie to set
     */
    public void setEspecie(String especie) {
        this.especie = especie;
    }

    /**
     * @return the raca
     */
    public String getRaca() {
        return raca;
    }

    /**
     * @param raca the raca to set
     */
    public void setRaca(String raca) {
        this.raca = raca;
    }

    /**
     * @return the porte
     */
    public String getPorte() {
        return porte;
    }

    /**
     * @param porte the porte to set
     */
    public void setPorte(String porte) {
        this.porte = porte;
    }

    /**
     * @return the sexo
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * @return the caracteristicasFisicas
     */
    public String getCaracteristicasFisicas() {
        return caracteristicasFisicas;
    }

    /**
     * @param caracteristicasFisicas the caracteristicasFisicas to set
     */
    public void setCaracteristicasFisicas(String caracteristicasFisicas) {
        this.caracteristicasFisicas = caracteristicasFisicas;
    }

    /**
     * @return the historicoDoencas
     */
    public String getHistoricoDoencas() {
        return historicoDoencas;
    }

    /**
     * @param historicoDoencas the historicoDoencas to set
     */
    public void setHistoricoDoencas(String historicoDoencas) {
        this.historicoDoencas = historicoDoencas;
    }

    /**
     * @return the medicacoes
     */
    public String getMedicacoes() {
        return medicacoes;
    }

    /**
     * @param medicacoes the medicacoes to set
     */
    public void setMedicacoes(String medicacoes) {
        this.medicacoes = medicacoes;
    }

    /**
     * @return the cpfResponsavel
     */
    public String getCpfResponsavel() {
        return cpfResponsavel;
    }

    /**
     * @param cpfResponsavel the cpfResponsavel to set
     */
    public void setCpfResponsavel(String cpfResponsavel) {
        this.cpfResponsavel = cpfResponsavel;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the datanascimento
     */
    public String getDatanascimento() {
        return datanascimento;
    }

    /**
     * @param datanascimento the datanascimento to set
     */
    public void setDatanascimento(String datanascimento) {
        this.datanascimento = datanascimento;
    }

}

