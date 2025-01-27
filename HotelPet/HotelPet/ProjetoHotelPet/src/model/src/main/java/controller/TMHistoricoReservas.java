/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Reserva;

/**
 *
 * @author neidi
 */
public class TMHistoricoReservas extends AbstractTableModel {
    
    private List<Reserva> lista;
    
    private final int COL_PET_NOME = 0;
    private final int COL_CHECK_IN = 1;
    private final int COL_CHECK_OUT = 2;
    private final int COL_SERVICOS = 3;
    private final int COL_VALOR_TOTAL = 4; 
    private final int COL_RESPONSAVEL = 5;
    private final int COL_CPF = 6;

    public TMHistoricoReservas(List<Reserva> listaReserva) {        
        lista = listaReserva;        
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {               
        Reserva reserva = new Reserva();
        if (lista.isEmpty()) {
            return reserva;
        } else {
            reserva = lista.get(rowIndex);

            //verifica qual valor deve ser retornado
            switch (columnIndex) {
                case COL_PET_NOME -> {
                    return reserva.getPet().getNome(); // Nome do pet
                }
                case COL_CHECK_IN -> {
                    return reserva.getCheckIn(); // Data de check-in
                }
                case COL_CHECK_OUT -> {
                    return reserva.getCheckOut(); // Data de check-out
                }
                case COL_SERVICOS -> {
                    // Combine os serviços selecionados em uma única string
                    StringBuilder servicos = new StringBuilder();
                    if (reserva.isServicoBanho()) servicos.append("Banho ");
                    if (reserva.isServicoTosa()) servicos.append("Tosa ");
                    if (reserva.isServicoPasseio()) servicos.append("Passeio ");
                    if (reserva.isServicoAlimentacaoEspecial()) servicos.append("Alimentação Especial");
                    return servicos.toString().trim(); // Serviços concatenados
                }
                case COL_VALOR_TOTAL -> {
                    return reserva.getValorTotal(); // Valor total da reserva
                }
                case COL_RESPONSAVEL ->{
                    return reserva.getCliente().getNome();
                }
                case COL_CPF ->{
                    return reserva.getCliente().getCpf();
                }
                default -> {
                    return null;
                }
            }
        }

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public String getColumnName(int column) {
        
        switch (column) {
            case COL_PET_NOME:
                return "Nome do Pet";
            case COL_CHECK_IN:
                return "Check-In";
            case COL_CHECK_OUT:
                return "Check-Out";
            case COL_SERVICOS:
                return "Serviços";
            case COL_VALOR_TOTAL:
                return "Valor Total";
            case COL_RESPONSAVEL:
                return "Responsavel";
            case COL_CPF:
                return "CPF";
            default:
                return "";
    }

}

    public List<Reserva> getLista() {
        return lista;
    }
}