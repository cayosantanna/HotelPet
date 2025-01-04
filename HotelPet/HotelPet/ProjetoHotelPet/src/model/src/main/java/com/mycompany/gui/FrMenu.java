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

/**
 *
 * @author cayo
 */
public class FrMenu extends javax.swing.JDialog {

    ReservaController reservaController = new ReservaController();

    public FrMenu(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
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
        List<Reserva> reservasPendentes = reservaController.verificarReservasSemCheckout();
        for (Reserva reserva : reservasPendentes) {
            if (reserva.getCheckIn() == null) {
                continue;
            }

            long diasHospedado = (new Date().getTime() - reserva.getCheckIn().getTime()) / (1000 * 60 * 60 * 24);

            if (diasHospedado >= 20) {
                String mensagem = String.format(
                        "ALERTA: Pet em estadia prolongada!\n\n"
                        + "Pet: %s\n"
                        + "Dias hospedado: %d\n"
                        + "Cliente: %s\n"
                        + "Telefone: %s",
                        reserva.getPet().getNome(),
                        diasHospedado,
                        reserva.getCliente().getNome(),
                        reserva.getCliente().getTelefone()
                );

                JOptionPane.showMessageDialog(this,
                        mensagem,
                        "Alerta de Estadia Prolongada",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
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
                .addGap(158, 158, 158)
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
                .addGap(41, 41, 41)
                .addComponent(btnLogout)
                .addContainerGap(49, Short.MAX_VALUE))
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
