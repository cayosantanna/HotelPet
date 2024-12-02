/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.valid;

import java.text.ParseException;
import java.util.Date;
import model.Reserva;
import model.exceptions.ReservaException;

/*

public class ValidateReserva {
    
    public Reserva validaCamposEntrada(boolean servicoBanho, boolean servicoTosa, boolean servicoPasseio,
                   boolean servicoAlimentacaoEspecial, Date checkIn, Date checkOut,
                   double valorTotal, Date dataReserva) throws ParseException{
        
        Reserva reserva = new Reserva();
        
        if (null == checkIn) {
            throw new ReservaException("Error - Campo vazio: 'Check In'.");
        } else {
        }
        
        if (null == checkOut) {
            throw new ReservaException("Error - Campo vazio: 'Check Out'.");
        } else {
        }
        
        if (checkOut.before(checkIn)) {
            throw new ReservaException("Error - A data 'checkOut' não pode ser anterior à data 'checkIn'.");
        }
        
         Date today = new Date();
        if (checkIn.before(today)) {
            throw new ReservaException("Error - A data 'checkIn' não pode ser no passado.");
        }
        
        // Verificar se checkOut é no passado
        if (checkOut.before(today)) {
            throw new ReservaException("Error - A data 'checkOut' não pode ser no passado.");
        }
        
        reserva.setCheckIn(checkIn);
        reserva.setCheckOut(checkOut);
        
        if (dataReserva == null) {
            throw new ReservaException("Error - Data da reserva não fornecida.");
        }
                
        return reserva;
    }

    public Reserva validaCamposEntrada(boolean servicoBanho, boolean servicoTosa, boolean servicoPasseio, boolean servicoAlimentacaoEspecial, Date checkIn, Date checkOut, double valorTotal) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}*/
public class ValidateReserva {
    
    public Reserva validaCamposEntrada(boolean servicoBanho, boolean servicoTosa, boolean servicoPasseio,
                   boolean servicoAlimentacaoEspecial, Date checkIn, Date checkOut,
                   double valorTotal, Date dataReserva) throws ParseException{
        
        Reserva reserva = new Reserva();
        
        if (null == checkIn) {
            throw new ReservaException("Error - Campo vazio: 'Check In'.");
        } else {
        }
        
        if (null == checkOut) {
            throw new ReservaException("Error - Campo vazio: 'Check Out'.");
        } else {
        }
        
        if (checkOut.before(checkIn)) {
            throw new ReservaException("Error - A data 'checkOut' não pode ser anterior à data 'checkIn'.");
        }
        
         Date today = new Date();
        if (checkIn.before(today)) {
            throw new ReservaException("Error - A data 'checkIn' não pode ser no passado.");
        }
        
        // Verificar se checkOut é no passado
        if (checkOut.before(today)) {
            throw new ReservaException("Error - A data 'checkOut' não pode ser no passado.");
        }
        
        reserva.setCheckIn(checkIn);
        reserva.setCheckOut(checkOut);
        
        if (dataReserva == null) {
            throw new ReservaException("Error - Data da reserva não fornecida.");
        }
                
        return reserva;
    }

    public Reserva validaCamposEntrada(boolean servicoBanho, boolean servicoTosa, boolean servicoPasseio, boolean servicoAlimentacaoEspecial, Date checkIn, Date checkOut, double valorTotal) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}

