/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.RelatorioFuncionario;
import model.Reserva;

/**
 *
 * @author neidi
 */
public class TMHistoricoReservas extends AbstractTableModel {
    private List<Reserva> lista;

    private final String[] colunas = {"CPF Cliente", "Nome Pet", "Check-In", "Check-Out", "Status Checkout"};
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public TMHistoricoReservas(List<Reserva> listaReserva) {        
        this.lista = listaReserva != null ? listaReserva : new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {               
        Reserva reserva = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return reserva.getCliente().getCpf();
            case 1:
                return reserva.getPet().getNome();
            case 2:
                return sdf.format(reserva.getCheckIn());
            case 3:
                if (reserva.getRelatorioFuncionario() != null && reserva.getRelatorioFuncionario().isFinalizado()) {
                    return sdf.format(reserva.getRelatorioFuncionario().getDataSaida());
                }
                return "Previsto: " + sdf.format(calcularPrevisaoCheckOut(reserva.getCheckIn()));
            case 4:
                return formatarStatus(reserva.getRelatorioFuncionario());
            default:
                return null;
        }
    }

    private Date calcularPrevisaoCheckOut(Date checkIn) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(checkIn);
        cal.add(Calendar.DAY_OF_MONTH, 20);
        return cal.getTime();
    }

    private String formatarStatus(RelatorioFuncionario relatorio) {
        if (relatorio == null) return "Aguardando Check-in";
        if (relatorio.isFinalizado()) return "Checkout Realizado";
        return "Em andamento desde " + sdf.format(relatorio.getDataEntrada());
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    public List<Reserva> getLista() {
        return lista;
    }

    public Reserva getReserva(int index) {
        if (index < 0 || index >= lista.size()) {
            throw new IndexOutOfBoundsException("Índice inválido: " + index);
        }
        return lista.get(index);
    }
}