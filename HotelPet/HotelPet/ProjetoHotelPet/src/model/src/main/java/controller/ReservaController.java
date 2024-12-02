/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ReservaDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Reserva;
import model.exceptions.ReservaException;
import model.valid.ValidateReserva;
/*public class ReservaController {
    
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
    
}*/

public class ReservaController {

    private ReservaDAO repositorio;

    public ReservaController() {
        this.repositorio = new ReservaDAO();
    }


    public void cadastrarReserva(boolean servicoBanho, boolean servicoTosa, boolean servicoPasseio,
                                 boolean servicoAlimentacaoEspecial, Date checkIn, Date checkOut,
                                 double valorTotal, Date dataReserva) throws ParseException {
        ValidateReserva valid = new ValidateReserva();
        Reserva novaReserva = valid.validaCamposEntrada(servicoBanho, servicoTosa, servicoPasseio, servicoAlimentacaoEspecial, checkIn, checkOut, valorTotal, dataReserva);
        repositorio.save(novaReserva); 
    }

  
    public void atualizarReserva(int idReserva, boolean servicoBanho, boolean servicoTosa, boolean servicoPasseio,
                                 boolean servicoAlimentacaoEspecial, Date checkIn, Date checkOut,
                                 double valorTotal, Date dataReserva) throws ParseException {
        ValidateReserva valid = new ValidateReserva();
        Reserva novaReserva = valid.validaCamposEntrada(servicoBanho, servicoTosa, servicoPasseio, servicoAlimentacaoEspecial, checkIn, checkOut, valorTotal, dataReserva);
        novaReserva.setId(idReserva);
        repositorio.update(novaReserva); 
    }

    /**
     * Atualiza a tabela de reservas
     */
    public void atualizarTabela(JTable grd) {
        List<Reserva> lst = repositorio.findAll(); 
        DefaultTableModel model = new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Check-In", "Check-Out", "Serviços", "Valor Total", "Data Reserva"}
        );

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Formato para a data

        for (Reserva reserva : lst) {
            model.addRow(new Object[]{
                reserva.getId(),
                dateFormat.format(reserva.getCheckIn()),  
                dateFormat.format(reserva.getCheckOut()), 
                (reserva.isServicoBanho() ? "Sim" : "Não"),
                (reserva.isServicoTosa() ? "Sim" : "Não"),
                (reserva.isServicoPasseio() ? "Sim" : "Não"),
                (reserva.isServicoAlimentacaoEspecial() ? "Sim" : "Não"),
                reserva.getValorTotal(),
                dateFormat.format(reserva.getDataReserva())  
            });
        }
        grd.setModel(model); 
    }


    public void excluirReserva(Reserva reserva) {
        if (reserva == null || reserva.getId() == 0) {
            throw new ReservaException("Erro - A reserva não existe ou não foi selecionada.");
        }
        repositorio.delete(reserva);
    }

  
    public Reserva buscarReservaPorNomePet(String nomePet) {
        if (nomePet == null || nomePet.trim().isEmpty()) {
            throw new ReservaException("Erro - Nome do pet não pode ser vazio.");
        }

        Reserva reserva = repositorio.findByNomePet(nomePet);
        if (reserva == null) {
            throw new ReservaException("Erro - Reserva não encontrada para o pet: " + nomePet);
        }

        return reserva;
    }
}
