/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.Date;
import model.Reserva;
import model.valid.ValidateReserva;


/**
 *
 * @author thais
 */
public class ReservaController {
    
     public void cadastrarReserva(boolean servicoBanho, boolean servicoTosa, boolean servicoPasseio,
                   boolean servicoAlimentacaoEspecial, Date checkIn, Date checkOut,
                   double valorTotal, Date dataReserva) {
        ValidateReserva valid = new ValidateReserva();
    }
    
}
