/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.valid;

import java.text.ParseException;
import java.util.Date;
import model.Reserva;
import model.exceptions.ReservaException;

/**
 *
 * @author thais
 */
public class ValidateReserva {
    
    public Reserva validaCamposEntrada(String nomePet, Date dataNascimento, String raca,
                   String caracteristicasFisicas, String historicoDoencas, String medicacoesNecessarias,
                   String observacoes, String especie, String porte, String sexo,
                   boolean servicoBanho, boolean servicoTosa, boolean servicoPasseio,
                   boolean servicoAlimentacaoEspecial, Date checkIn, Date checkOut,
                   double valorTotal, Date dataReserva) throws ParseException{
        
        Reserva reserva = new Reserva();
        
        if (nomePet == null || nomePet.trim().isEmpty()) {
            throw new ReservaException("Erro - Campo vazio: 'nome do Pet'.");
        }
        reserva.setNomePet(nomePet);
                
        return reserva;
    }
}

