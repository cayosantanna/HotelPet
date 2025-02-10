/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.util.Date;

import dao.RelatorioFuncionarioDAO;
import model.RelatorioFuncionario;
import model.Reserva;

/**
 *
 * @author famil
 */
public class RelatorioFuncionarioController {
    private static final double VALOR_DIARIA = 75.0;
    private static final double MULTA_PERCENTUAL = 0.20;
    private static final int DIAS_LIMITE = 20;
    private final RelatorioFuncionarioDAO dao;
    
    public RelatorioFuncionarioController(Connection connection) {
        this.dao = new RelatorioFuncionarioDAO(connection);
    }
    
    public double calcularValorAdicional(Reserva reserva) {
        if (reserva == null || reserva.getCheckIn() == null) return 0.0;
        
        Date hoje = new Date();
        double valorAdicional = 0.0;
        
        if (reserva.getCheckOut() != null && hoje.after(reserva.getCheckOut())) {
            long diasAtraso = calcularDias(reserva.getCheckOut(), hoje);
            double valorDiasExtras = diasAtraso * VALOR_DIARIA;
            valorAdicional = valorDiasExtras + (valorDiasExtras * MULTA_PERCENTUAL);
        } else if (reserva.getCheckOut() == null) {
            long diasTotais = calcularDias(reserva.getCheckIn(), hoje);
            valorAdicional = diasTotais * VALOR_DIARIA;
        }
        
        return valorAdicional;
    }
    
    private long calcularDias(Date inicio, Date fim) {
        return (fim.getTime() - inicio.getTime()) / (1000 * 60 * 60 * 24);
    }

    public void finalizarRelatorio(RelatorioFuncionario relatorio, Reserva reserva) throws Exception {
        if (relatorio == null || reserva == null) {
            throw new IllegalArgumentException("Relatório e reserva não podem ser nulos");
        }
        
        // Atualiza checkout e finaliza relatório
        relatorio.setFinalizado(true);
        relatorio.setDataSaida(new java.sql.Date(new Date().getTime()));
        
        // Calcula valor final
        double valorAdicional = calcularValorAdicional(reserva);
        relatorio.setValorTotal(relatorio.getValorTotal() + valorAdicional);
        
        // Atualiza reserva
        reserva.setCheckOut(new Date());
        new ReservaController().salvarReservaComValidacao(reserva);
        
        // Salva relatório
        dao.update(relatorio, relatorio);
    }
    
    public void salvarRelatorio(RelatorioFuncionario relatorio) throws Exception {
        validarRelatorio(relatorio);
        dao.update(relatorio, relatorio);
    }
    
    public boolean verificarEstadiaLonga(Reserva reserva) {
        if (reserva == null || reserva.getCheckIn() == null) return false;
        
        Date hoje = new Date();
        long diasEstadia = (hoje.getTime() - reserva.getCheckIn().getTime()) / (1000 * 60 * 60 * 24);
        
        // Caso 1: Mais de 20 dias sem checkout
        if (reserva.getCheckOut() == null && diasEstadia >= DIAS_LIMITE) {
            return true;
        }
        
        // Caso 2: Passou da data prevista de checkout
        if (reserva.getCheckOut() != null && hoje.after(reserva.getCheckOut())) {
            return true;
        }
        
        return false;
    }
    
    private void validarRelatorio(RelatorioFuncionario relatorio) throws Exception {
        if (relatorio == null) {
            throw new IllegalArgumentException("Relatório não pode ser nulo");
        }
        if (relatorio.isFinalizado()) {
            throw new Exception("Relatório já foi finalizado e não pode ser alterado");
        }
    }
    
    public boolean isCheckoutAtrasado(Reserva reserva) {
        if (reserva.getCheckOut() == null) return false;
        return new Date().after(reserva.getCheckOut());
    }
}
