/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reserva {

    private int id;
    private String NomePet;
    private boolean servicoBanho;
    private boolean servicoTosa;
    private boolean servicoPasseio;
    private boolean servicoAlimentacaoEspecial;
    private Date checkIn;
    private Date checkOut;
    private double valorTotal;
    private Date dataReserva;
    private Pet pet; // Cada reserva tem um pet associado

    public Reserva() throws ParseException {
        super();
        this.servicoBanho = true;
        this.servicoTosa = true;
        this.servicoPasseio = true;
        this.servicoAlimentacaoEspecial = true;
        this.checkIn = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1970");
        this.checkOut = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1970");
        this.valorTotal = 0;
        this.dataReserva = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1970");
        this.pet = pet;
    }

    public Reserva(
            int id,
            boolean servicoBanho,
            boolean servicoTosa,
            boolean servicoPasseio,
            boolean servicoAlimentacaoEspecial,
            Date checkIn,
            Date checkOut,
            double valorTotal,
            Date dataReserva
    ) {
        this.servicoBanho = servicoBanho;
        this.servicoTosa = servicoTosa;
        this.servicoPasseio = servicoPasseio;
        this.servicoAlimentacaoEspecial = servicoAlimentacaoEspecial;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.valorTotal = valorTotal;
        this.dataReserva = dataReserva;
        this.pet = pet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    /**
     * @return the NomePet
     */
    public String getNomePet() {
         return NomePet;
    }
    
    /**
     * @param NomePet the NomePet to set
     */
    public void setNomePet(String NomePet) {
        this.NomePet = NomePet;
    }
    
     public Pet getPet() {
        return pet;
    }
    public void setPet(Pet pet){
        this.pet = pet;
    }

   
}
