/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ReservaDAO;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.swing.JTable;
import model.Reserva;
import model.exceptions.ReservaException;
import model.valid.ValidateReserva;


/**
 *
 * @author thais
 */
public class ReservaController {
    
    private ReservaDAO repositorio;

    public ReservaController() {
        repositorio = new ReservaDAO();
    }
    
     public void cadastrarReserva(boolean servicoBanho, boolean servicoTosa, boolean servicoPasseio,
                   boolean servicoAlimentacaoEspecial, Date checkIn, Date checkOut,
                   double valorTotal, Date dataReserva) {
        ValidateReserva valid = new ValidateReserva();
    }
     
     public void atualizarReserva(int idReserva, boolean servicoBanho, boolean servicoTosa, boolean servicoPasseio,
                   boolean servicoAlimentacaoEspecial, Date checkIn, Date checkOut,
                   double valorTotal, Date dataReserva) throws ParseException {
        ValidateReserva valid = new ValidateReserva();
        Reserva novaReserva = valid.validaCamposEntrada(servicoBanho, servicoTosa, servicoPasseio, servicoAlimentacaoEspecial, checkIn, checkOut, valorTotal, dataReserva);
        novaReserva.setId(idReserva);
        
        repositorio.update(novaReserva);
    }

    //public Reserva buscarReserva() {
        //irá buscar pelo nome do pet
        //return (Reserva) 
    //}

    public void atualizarTabela(JTable grd) {
        List<Object> lst = repositorio.findAll();
        
        TMCadReserva tmReserva = new TMCadReserva(lst);
        grd.setModel(tmReserva);        
    }

    public void excluirReserva(Reserva reserva) {
        if (reserva != null) {
            repositorio.delete(reserva);
        } else {
            throw new ReservaException("Error - Reserva inexistente.");
        }
    }    
    
}
