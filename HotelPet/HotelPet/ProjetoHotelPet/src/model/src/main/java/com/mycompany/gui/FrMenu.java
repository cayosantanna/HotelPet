/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.mycompany.gui;

import controller.ReservaController;
import java.util.List;
import javax.swing.JOptionPane;
import model.Reserva;
import java.util.Date;
import controller.RelatorioFuncionarioController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author cayo
 */
public class FrMenu extends javax.swing.JDialog {
    private Connection connection;
    private ReservaController reservaController;
    private RelatorioFuncionarioController relatorioController;

    public FrMenu(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);
        
        try {
            // Estabelece a conexão com o banco de dados
            this.connection = DriverManager.getConnection(
                "jdbc:mysql://hotelpetserver.mysql.database.azure.com:3306/hotelpet?useSSL=true&requireSSL=true&serverTimezone=UTC", "hotelpet", "Hotel123456789");
            
            // Inicializa os controllers
            this.reservaController = new ReservaController();
            this.relatorioController = new RelatorioFuncionarioController(connection);
            
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao conectar ao banco: " + e.getMessage());
        }
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    public void setFuncionario(Boolean funcionario) {
        this.btnBuscaCliente.setEnabled(funcionario);
        this.btnCadastroCliente.setEnabled(funcionario);
        this.btnHistoricoReserva.setEnabled(funcionario);
        if (funcionario) {
            this.verificarAlertas();
        }
    }

    // Método para verificar alertas de estadias longas
    private void verificarAlertas() {
        try {
            List<Reserva> reservasPendentes = reservaController.verificarReservasSemCheckout();
            
            for (Reserva reserva : reservasPendentes) {
                if (relatorioController.verificarEstadiaLonga(reserva)) {
                    double valorAdicional = relatorioController.calcularValorAdicional(reserva);
                    
                    String mensagem = String.format(
                        "ATENÇÃO: Pet %s com estadia prolongada\n" +
                        "Cliente: %s\n" +
                        "Telefone: %s\n" +
                        "Dias de estadia: %d\n" +
                        "Valor adicional: R$ %.2f",
                        reserva.getPet().getNome(),
                        reserva.getCliente().getNome(),
                        reserva.getCliente().getTelefone(),
                        calcularDiasEstadia(reserva.getCheckIn(), new Date()),
                        valorAdicional
                    );
                    
                    JOptionPane.showMessageDialog(this, 
                        mensagem, 
                        "Alerta de Estadia", 
                        JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Erro ao verificar alertas: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private long calcularDiasEstadia(Date inicio, Date fim) {
        return (fim.getTime() - inicio.getTime()) / (1000 * 60 * 60 * 24);
    }

    private void exibirAlerta(Reserva reserva, long diasEstadia, String mensagemAdicional) {
        String mensagem = String.format(
            "%s\n\nPet: %s\n" +
            "Dias hospedado: %d\n" +
            "Cliente: %s\n" +
            "Telefone: %s",
            mensagemAdicional,
            reserva.getPet().getNome(),
            diasEstadia,
            reserva.getCliente().getNome(),
            reserva.getCliente().getTelefone()
        );
        
        JOptionPane.showMessageDialog(this,
            mensagem,
            "Alerta de Estadia",
            JOptionPane.WARNING_MESSAGE);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCadastroCliente = new javax.swing.JButton();
        btnBuscaCliente = new javax.swing.JButton();
        btnServicos = new javax.swing.JButton();
        btnHistoricoReserva = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnCadastroCliente.setText("Cadastro Cliente");
        btnCadastroCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastroClienteActionPerformed(evt);
            }
        });

        btnBuscaCliente.setText("Busca Cliente");
        btnBuscaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscaClienteActionPerformed(evt);
            }
        });

        btnServicos.setText("Serviços");
        btnServicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnServicosActionPerformed(evt);
            }
        });

        btnHistoricoReserva.setText("Historico Reservas");
        btnHistoricoReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoricoReservaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 36)); // NOI18N
        jLabel1.setText("Hotel Pet");

        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnHistoricoReserva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCadastroCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBuscaCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                    .addComponent(btnServicos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(52, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHistoricoReserva)
                    .addComponent(btnBuscaCliente))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCadastroCliente)
                    .addComponent(btnServicos))
                .addGap(40, 40, 40)
                .addComponent(btnLogout)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastroClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastroClienteActionPerformed
        DlgCadCliente telaCadCliente = new DlgCadCliente(new javax.swing.JFrame(), true);
        telaCadCliente.setVisible(true);         // TODO add your handling code here:
    }//GEN-LAST:event_btnCadastroClienteActionPerformed

    private void btnBuscaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaClienteActionPerformed
        DlgBuscaUsuario telaBuscaUsuario = new DlgBuscaUsuario(new javax.swing.JFrame(), true);
        telaBuscaUsuario.setVisible(true);         // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscaClienteActionPerformed

    private void btnHistoricoReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoricoReservaActionPerformed
        DlgHistoricoReservas telaHistorico = new DlgHistoricoReservas(new javax.swing.JFrame(), true);
        telaHistorico.setVisible(true);
    }//GEN-LAST:event_btnHistoricoReservaActionPerformed

    private void btnServicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnServicosActionPerformed
        DlgServicos telaServiços = new DlgServicos(new javax.swing.JFrame(), true);
        telaServiços.setVisible(true);         // TODO add your handling code here:
    }//GEN-LAST:event_btnServicosActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        this.dispose();
        this.setVisible(false);
    }//GEN-LAST:event_btnLogoutActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscaCliente;
    private javax.swing.JButton btnCadastroCliente;
    private javax.swing.JButton btnHistoricoReserva;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnServicos;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

}
