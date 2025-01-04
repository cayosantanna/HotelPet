package model.valid;

import java.text.ParseException;
import java.util.Date;

import model.Reserva;
import model.exceptions.ReservaException;

public class ValidateReserva {

    public static boolean validarDescricaoServicosExtras(String descricao) {
        if(descricao == null || descricao.trim().isEmpty()) {
            return true; // Campo opcional
        }
        // Implementar regras de validação, por exemplo, tamanho máximo
        return descricao.length() <= 255;
    }

    public Reserva validaCamposEntrada(boolean servicoBanho, boolean servicoTosa, boolean servicoPasseio,
                                       boolean servicoAlimentacaoEspecial, Date checkIn, Date checkOut,
                                       double valorTotal, Date dataReserva) throws ReservaException, ParseException {
        
        // Verificação de campos nulos
        if (checkIn == null) {
            throw new ReservaException("Error - Campo vazio: 'Check In'.");
        }
        
        if (checkOut == null) {
            throw new ReservaException("Error - Campo vazio: 'Check Out'.");
        }

        // Verificação das datas
        Date today = new Date();
        
        if (checkIn.before(today)) {
            throw new ReservaException("Error - A data 'Check In' não pode ser no passado.");
        }
        
        if (checkOut.before(today)) {
            throw new ReservaException("Error - A data 'Check Out' não pode ser no passado.");
        }
        
        if (checkOut.before(checkIn)) {
            throw new ReservaException("Error - A data 'Check Out' não pode ser anterior à data 'Check In'.");
        }
        
        // Verificar a data da reserva
        if (dataReserva == null) {
            throw new ReservaException("Error - Data da reserva não fornecida.");
        }

        // Verificação do valor total
        if (valorTotal <= 0) {
            throw new ReservaException("Error - O 'Valor Total' precisa ser maior que zero.");
        }
        

        // Criação do objeto reserva e atribuição dos dados
        Reserva reserva = new Reserva();
        reserva.setCheckIn(checkIn);
        reserva.setCheckOut(checkOut);
        reserva.setDataReserva(dataReserva);
        reserva.setValorTotal(valorTotal);

        // Retorna a reserva validada
        return reserva;
    }
}
