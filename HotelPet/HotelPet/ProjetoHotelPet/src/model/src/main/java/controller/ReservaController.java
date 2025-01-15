package controller;

import dao.ReservaDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import model.Cliente;
import model.Pet;
import model.Reserva;

public class ReservaController {

    

    private ReservaDAO reservaDAO;

    public ReservaController() {
        this.reservaDAO = new ReservaDAO();
    }

    public List<Reserva> verificarReservasSemCheckout() {
        return reservaDAO.findReservasSemCheckout();
    }

    public int calcularDiasEstadia(Date checkIn, Date checkOut) {
        long diffMillis = checkOut.getTime() - checkIn.getTime();
        return (int) (diffMillis / (1000 * 60 * 60 * 24));
    }

    public void salvarReservaComValidacao(Reserva reserva) throws Exception {
        if (reserva.getCheckIn() == null) {
            throw new Exception("A data de Check-In é obrigatória.");
        }
        if (reserva.getCheckOut() == null) {
            reserva.setCheckOut(calcularCheckOutAutomatico(reserva.getCheckIn()));
        }
        int diasDeEstadia = calcularDiasEstadia(reserva.getCheckIn(), reserva.getCheckOut());
        if (diasDeEstadia > 20) {
            diasDeEstadia = 20;
        }
        double valorTotal = diasDeEstadia * 75.0;
        if (reserva.isServicoBanho()) {
            valorTotal += 90.0;
        }
        if (reserva.isServicoTosa()) {
            valorTotal += 70.0;
        }
        if (reserva.isServicoPasseio()) {
            valorTotal += 60.0;
        }
        if (reserva.isServicoAlimentacaoEspecial()) {
            valorTotal += 100.0;
        }
        reserva.setValorTotal(valorTotal);
        reservaDAO.save(reserva);
    }

    public boolean salvarReserva(Cliente cliente, Pet pet, String checkIn, String checkOut) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date checkInDate = sdf.parse(checkIn);
            Date checkOutDate = (checkOut != null && !checkOut.isEmpty()) ? sdf.parse(checkOut) : calcularCheckOutAutomatico(checkInDate);

            int diasDeEstadia = calcularDiasEstadia(checkInDate, checkOutDate);
            if (diasDeEstadia > 20) {
                diasDeEstadia = 20;
            }
            double valorTotal = diasDeEstadia * 75.0;

            Reserva reserva = new Reserva();
            reserva.setCliente(cliente);
            reserva.setPet(pet);
            reserva.setCheckIn(checkInDate);
            reserva.setCheckOut(checkOutDate);
            reserva.setValorTotal(valorTotal);

            reservaDAO.save(reserva);
            return true; // Reserva salva com sucesso

        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Erro ao processar as datas. Verifique o formato: dd/MM/yyyy", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar a reserva: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private Date calcularCheckOutAutomatico(Date checkIn) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(checkIn);
        calendar.add(Calendar.DAY_OF_MONTH, 20);
        return calendar.getTime();
    }
    


    public List<Reserva> buscarReservasPorCpfOuNomePet(String cpfCliente, String nomePet) {
        return reservaDAO.findByCpfOrPetName(cpfCliente, nomePet);
    }
    
}
