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
 * @author thais
 */
public class TMCadReserva extends AbstractTableModel{
    private List<Object> lista;
    
    private final int COL_SERVICOBANHO = 0;   
    private final int COL_SERVICOTOSA = 1;    
    private final int COL_SERVICOPASSEIO = 2;
    private final int COL_SERVICOALIMENTACAOESPECIAL = 3;       
    private final int COL_CHECKIN = 4;
    private final int COL_CHECKOUT = 5;
    private final int COL_VALORTOTAL = 6;
    private final int COL_DATARESERVA = 7;

    public TMCadReserva(List<Object> lstReservas) {        
        lista = lstReservas;        
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {               
            Reserva aux = new Reserva();
        if (lista.isEmpty()) {
            return aux;
        } else {
            aux = (Reserva) lista.get(rowIndex);

            //verifica qual valor deve ser retornado
            switch (columnIndex) {
                case -1:
                    return aux;
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
                    break;
            }
        }
        return aux;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public String getColumnName(int column) {
        
        switch (column) {
            case COL_SERVICOBANHO:
                return "Servico Banho";
            case COL_SERVICOTOSA:
                return "Servico Tosa";
            case COL_SERVICOPASSEIO:
                return "Servico Passeio"; 
            case COL_SERVICOALIMENTACAOESPECIAL:
                return "Servico Alimentacao Especial";
            case COL_CHECKIN:
                return "Check In";
            case COL_CHECKOUT:
                return "Check Out";
            case COL_VALORTOTAL:
                return "Valor Total";
            case COL_DATARESERVA:
                return "Data ";
            
            default:
                break;
        }

        return "";
    }

    @Override
    public Class getColumnClass(int columnIndex) {
//        if(columnIndex == COL_MATRICULA)
//            return Boolean.class;
        
        return String.class;
    }
}
