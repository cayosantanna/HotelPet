/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.mycompany.gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import java.awt.Frame;
import javax.swing.JList;

/**
 *
 * @author cayo
 */
import controller.ClienteController;
import model.Cliente;
import javax.swing.DefaultListModel;

public class DlgBuscaUsuario extends javax.swing.JDialog {

    private ClienteController clienteController;

    public DlgBuscaUsuario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        clienteController = new ClienteController(true); 
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        edtNome = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstBuscaUsuario = new javax.swing.JList<>();
        btnBusca = new javax.swing.JButton();
        btnReserva = new javax.swing.JButton();
        btnEditarCliente = new javax.swing.JButton();
        lblCPF = new javax.swing.JLabel();
        edtCPF = new javax.swing.JTextField();
        btnNovoPet = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Liberation Sans", 1, 36)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Buscar Cliente");

        lblNome.setText("Nome:");

        edtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtNomeActionPerformed(evt);
            }
        });

        lstBuscaUsuario.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lstBuscaUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstBuscaUsuarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lstBuscaUsuario);

        btnBusca.setText("Buscar");
        btnBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscaActionPerformed(evt);
            }
        });

        btnReserva.setText("Reserva");
        btnReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservaActionPerformed(evt);
            }
        });

        btnEditarCliente.setText("Editar");
        btnEditarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarClienteActionPerformed(evt);
            }
        });

        lblCPF.setText("CPF:");

        btnNovoPet.setText("Novo Pet");
        btnNovoPet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoPetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNome)
                                .addGap(34, 34, 34))
                            .addComponent(btnBusca, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(223, 223, 223)
                                .addComponent(lblCPF))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btnReserva)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(btnEditarCliente)
                                .addGap(18, 18, 18)
                                .addComponent(btnNovoPet))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(edtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(edtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome)
                    .addComponent(lblCPF))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnBusca)
                            .addComponent(btnReserva)
                            .addComponent(btnEditarCliente)
                            .addComponent(btnNovoPet))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void edtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtNomeActionPerformed

    private void btnBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaActionPerformed
 
    String nomeBusca = edtNome.getText().trim();
    String cpfBusca = edtCPF.getText().trim();

    List<Cliente> clientes = clienteController.listarTodosClientes();
    DefaultListModel<String> listModel = new DefaultListModel<>();

    for (Cliente cliente : clientes) {
        if (!nomeBusca.isEmpty() && cliente.getNome().toLowerCase().contains(nomeBusca.toLowerCase()) ||
            !cpfBusca.isEmpty() && cliente.getCpf().equals(cpfBusca)) {
            listModel.addElement(cliente.getNome() + " - CPF: " + cliente.getCpf());
        }
    }

    if (listModel.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Nenhum cliente encontrado.", "Busca", JOptionPane.INFORMATION_MESSAGE);
    }

    lstBuscaUsuario.setModel(listModel);

    }//GEN-LAST:event_btnBuscaActionPerformed

    private void btnReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservaActionPerformed
    DlgBuscaPet telabuscapet = new DlgBuscaPet(new javax.swing.JFrame(), true);
    telabuscapet.setVisible(true);       
    }//GEN-LAST:event_btnReservaActionPerformed

    private void btnEditarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarClienteActionPerformed
       
    String selecionado = lstBuscaUsuario.getSelectedValue();

    if (selecionado != null) {
        if (!selecionado.contains(" - CPF: ")) {
            JOptionPane.showMessageDialog(this, "Formato inválido da entrada selecionada.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String cpf = selecionado.split(" - CPF: ")[1];
        Cliente cliente = clienteController.buscarClientePorCPF(cpf);

        if (cliente != null) {
            DlgCadCliente dlgCadCliente = new DlgCadCliente(new javax.swing.JFrame(), true);
            dlgCadCliente.setCliente(cliente);
            dlgCadCliente.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Cliente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Por favor, selecione um cliente.", "Aviso", JOptionPane.WARNING_MESSAGE);
    }
    }//GEN-LAST:event_btnEditarClienteActionPerformed

    private void lstBuscaUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstBuscaUsuarioMouseClicked
   String nomeSelecionado = lstBuscaUsuario.getSelectedValue();
    if (nomeSelecionado != null) {
        Cliente cliente = clienteController.listarTodosClientes().stream()
            .filter(c -> c.getNome().equals(nomeSelecionado))
            .findFirst()
            .orElse(null);

        if (cliente != null) {
           
            JOptionPane.showMessageDialog(this, "Cliente selecionado:\nNome: " + cliente.getNome() +
                                              "\nCPF: " + cliente.getCpf() + 
                                              "\nEmail: " + cliente.getEmail() +
                                              "\nTelefone: " + cliente.getTelefone(), 
                                          "Detalhes do Cliente", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    }//GEN-LAST:event_lstBuscaUsuarioMouseClicked

    private void btnNovoPetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoPetActionPerformed
    String selecionado = lstBuscaUsuario.getSelectedValue();

        if (selecionado != null) {
            String cpf = selecionado.split(" - CPF: ")[1];
            DlgCadPet dlgCadPet = new DlgCadPet(new javax.swing.JFrame(), true, cpf);
            dlgCadPet.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um cliente para cadastrar um pet.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnNovoPetActionPerformed
    
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBusca;
    private javax.swing.JButton btnEditarCliente;
    private javax.swing.JButton btnNovoPet;
    private javax.swing.JButton btnReserva;
    private javax.swing.JTextField edtCPF;
    private javax.swing.JTextField edtNome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCPF;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JList<String> lstBuscaUsuario;
    // End of variables declaration//GEN-END:variables
}
