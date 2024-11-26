/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author thais
 */
public class Reserva {
    private int id;
    private String nomePet;
    private Date dataNascimento;
    private String raca;
    private String caracteristicasFisicas;
    private String historicoDoencas;
    private String medicacoesNecessarias;
    private String observacoes;
    private String especie;
    private String porte;
    private String sexo;
    private boolean servicoBanho;
    private boolean servicoTosa;
    private boolean servicoPasseio;
    private boolean servicoAlimentacaoEspecial;
    private Date checkIn;
    private Date checkOut;
    private double valorTotal;
    private Date dataReserva;
    
    public Reserva() throws ParseException {
        super();
        this.nomePet = "";
        this.dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1970");
        this.raca = "";
        this.caracteristicasFisicas = "";
        this.historicoDoencas = "";
        this.medicacoesNecessarias = "";
        this.observacoes = "";
        this.especie = "";
        this.porte = "";
        this.sexo = "";
        this.servicoBanho = true;
        this.servicoTosa = true;
        this.servicoPasseio = true;
        this.servicoAlimentacaoEspecial = true;
        this.checkIn = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1970");;
        this.checkOut = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1970");;
        this.valorTotal = 0;
        this.dataReserva = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1970");
    }
    
    public Reserva(String nomePet, Date dataNascimento, String raca,
                   String caracteristicasFisicas, String historicoDoencas, String medicacoesNecessarias,
                   String observacoes, String especie, String porte, String sexo,
                   boolean servicoBanho, boolean servicoTosa, boolean servicoPasseio,
                   boolean servicoAlimentacaoEspecial, Date checkIn, Date checkOut,
                   double valorTotal, Date dataReserva) {
        this.nomePet = nomePet;
        this.dataNascimento = dataNascimento;
        this.raca = raca;
        this.caracteristicasFisicas = caracteristicasFisicas;
        this.historicoDoencas = historicoDoencas;
        this.medicacoesNecessarias = medicacoesNecessarias;
        this.observacoes = observacoes;
        this.especie = especie;
        this.porte = porte;
        this.sexo = sexo;
        this.servicoBanho = servicoBanho;
        this.servicoTosa = servicoTosa;
        this.servicoPasseio = servicoPasseio;
        this.servicoAlimentacaoEspecial = servicoAlimentacaoEspecial;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.valorTotal = valorTotal;
        this.dataReserva = dataReserva;
    }
    
    public String getNomePet() {
        return nomePet;
    }

    public void setNomePet(String nomePet) {
        this.nomePet = nomePet;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
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

    public String getMedicacoesNecessarias() {
        return medicacoesNecessarias;
    }

    public void setMedicacoesNecessarias(String medicacoesNecessarias) {
        this.medicacoesNecessarias = medicacoesNecessarias;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
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

    public boolean isServicoBanho() {
        return servicoBanho;
    }

    public void setServicoBanho(boolean servicoBanho) {
        this.servicoBanho = servicoBanho;
    }

    public boolean isServicoTosa() {
        return servicoTosa;
    }

    public void setServicoTosa(boolean servicoTosa) {
        this.servicoTosa = servicoTosa;
    }

    public boolean isServicoPasseio() {
        return servicoPasseio;
    }

    public void setServicoPasseio(boolean servicoPasseio) {
        this.servicoPasseio = servicoPasseio;
    }

    public boolean isServicoAlimentacaoEspecial() {
        return servicoAlimentacaoEspecial;
    }

    public void setServicoAlimentacaoEspecial(boolean servicoAlimentacaoEspecial) {
        this.servicoAlimentacaoEspecial = servicoAlimentacaoEspecial;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Date getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(Date dataReserva) {
        this.dataReserva = dataReserva;
    }
}

