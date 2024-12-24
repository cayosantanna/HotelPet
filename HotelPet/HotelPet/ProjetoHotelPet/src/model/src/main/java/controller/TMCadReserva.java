/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Reserva;

public class TMCadReserva extends AbstractTableModel {
    private List<Reserva> lista;

    private final int COL_SERVICOBANHO = 0;   
    private final int COL_SERVICOTOSA = 1;    
    private final int COL_SERVICOPASSEIO = 2;
    private final int COL_SERVICOALIMENTACAOESPECIAL = 3;       
    private final int COL_CHECKIN = 4;
    private final int COL_CHECKOUT = 5;
    private final int COL_VALORTOTAL = 6;
    private final int COL_DATARESERVA = 7;

    public TMCadReserva(List<Reserva> lstReservas) {        
        this.lista = lstReservas;        
    }

    @Override
    public int getRowCount() {
        return lista != null ? lista.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {               
        if (lista.isEmpty()) {
            return null;
        }
        Reserva aux = lista.get(rowIndex);

        switch (columnIndex) {
            case COL_SERVICOBANHO:
                return aux.isServicoBanho();
            case COL_SERVICOTOSA:
                return aux.isServicoTosa();
            case COL_SERVICOPASSEIO:
                return aux.isServicoPasseio();
            case COL_SERVICOALIMENTACAOESPECIAL:
                return aux.isServicoAlimentacaoEspecial();
            case COL_CHECKIN:
                return aux.getCheckIn();
            case COL_CHECKOUT:
                return aux.getCheckOut();
            case COL_VALORTOTAL:
                return aux.getValorTotal();
            case COL_DATARESERVA:
                return aux.getDataReserva();
            default: 
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case COL_SERVICOBANHO:
                return "Serviço Banho";
            case COL_SERVICOTOSA:
                return "Serviço Tosa";
            case COL_SERVICOPASSEIO:
                return "Serviço Passeio"; 
            case COL_SERVICOALIMENTACAOESPECIAL:
                return "Serviço Alimentação Especial";
            case COL_CHECKIN:
                return "Check-In";
            case COL_CHECKOUT:
                return "Check-Out";
            case COL_VALORTOTAL:
                return "Valor Total";
            case COL_DATARESERVA:
                return "Data Reserva";
            default:
                return "";
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case COL_SERVICOBANHO:
            case COL_SERVICOTOSA:
            case COL_SERVICOPASSEIO:
            case COL_SERVICOALIMENTACAOESPECIAL:
                return Boolean.class;
            case COL_CHECKIN:
            case COL_CHECKOUT:
            case COL_DATARESERVA:
                return java.util.Date.class;
            case COL_VALORTOTAL:
                return Double.class;
            default:
                return String.class;
        }
    }
}
