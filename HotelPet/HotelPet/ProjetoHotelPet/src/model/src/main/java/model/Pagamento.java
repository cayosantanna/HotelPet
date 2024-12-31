/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author thais
 */
public class Pagamento {
    private Reserva reserva;
    private String metodoPagamento;
    private int parcelas;

    public Pagamento(Reserva reserva, String metodoPagamento, int parcelas) {
        this.reserva = reserva;
        this.metodoPagamento = metodoPagamento;
        this.parcelas = parcelas;
    }

    // Getters e Setters
    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }
}
