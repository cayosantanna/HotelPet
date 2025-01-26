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
    
    private final int COL_NOMEPET = 0;   
    private final int COL_CPF = 1;          

    public TMHistoricoReservas(List<Reserva> listaReserva) {        
        lista = listaReserva;        
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {               
        Reserva aux = new Reserva();
        if (lista.isEmpty()) {
            return aux;
        } else {
            aux = lista.get(rowIndex);

            //verifica qual valor deve ser retornado
            switch (columnIndex) {
                case -1:
                    return aux;
                case COL_NOMEPET:
                    return aux.getPet();
                case COL_CPF:
                    return aux.getCliente();

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
            case COL_NOMEPET:
                return "NamePet";
            case COL_CPF:
                return "CPF";
            
            default:
                break;
        }

        return "";
    }

}
