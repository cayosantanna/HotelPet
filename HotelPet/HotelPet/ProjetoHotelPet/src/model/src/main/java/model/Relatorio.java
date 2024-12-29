/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import model.Reserva;

/**
 *
 * @author thais
 */

public class Relatorio {
    private Reserva reserva;
    
    public Relatorio (Reserva reserva){
        this.reserva = reserva;
    }
    
    public Reserva getReserva() {
        return reserva;
    }
    
    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
}
