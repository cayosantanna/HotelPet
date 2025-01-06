/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.mycompany.gui;

import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author cayo
 */
import controller.ClienteController;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import javax.swing.DefaultListModel;
import javax.swing.text.MaskFormatter;

public class DlgBuscaUsuario extends javax.swing.JDialog {

    private ClienteController clienteController;

    public DlgBuscaUsuario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.clienteController = new ClienteController();
        this.buscaClientes();
        this.adicionarMascaraNosCampos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        buscaNome = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstBuscaUsuario = new javax.swing.JList<>();
        btnBusca = new javax.swing.JButton();
        btnReserva = new javax.swing.JButton();
        btnEditarCliente = new javax.swing.JButton();
        lblCPF = new javax.swing.JLabel();
        btnNovoPet = new javax.swing.JButton();
        buscaCpf = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Liberation Sans", 1, 36)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Buscar Cliente");

        lblNome.setText("Nome:");

        lstBuscaUsuario.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lstBuscaUsuario.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                lstBuscaUsuarioAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
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
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buscaNome, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(30, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNome)
                                .addGap(34, 34, 34))
                            .addComponent(btnBusca, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btnReserva)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(btnEditarCliente)
                                .addGap(18, 18, 18)
                                .addComponent(btnNovoPet)
                                .addGap(75, 75, 75))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(223, 223, 223)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblCPF)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(buscaCpf)
                                        .addGap(30, 30, 30))))))))
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
                    .addComponent(buscaNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscaCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void buscaClientes() {
        List<Cliente> clientes = clienteController.listarTodosClientes();
        DefaultListModel<String> listModel = new DefaultListModel<String>();

        for (Cliente cliente : clientes) {
            listModel.addElement("ID: " + cliente.getId() + " - Nome: " + cliente.getNome() + " - CPF: " + cliente.getCpf());
        }

        lstBuscaUsuario.setModel(listModel);
    }

    private void buscaClientes(String nome, String cpf) {
        List<Cliente> clientes = clienteController.listarTodosClientes(nome, cpf);
        DefaultListModel<String> listModel = new DefaultListModel<String>();

        if (clientes == null || clientes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum cliente encontrado.", "Busca", JOptionPane.INFORMATION_MESSAGE);
        }

        for (Cliente cliente : clientes) {
            listModel.addElement("ID: " + cliente.getId() + " - Nome: " + cliente.getNome() + " - CPF: " + cliente.getCpf());
        }

        lstBuscaUsuario.setModel(listModel);
    }

    private void btnBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaActionPerformed
        String nome = buscaNome.getText().trim();
        String cpf = buscaCpf.getText().trim().replaceAll("[^\\d]", "");

        this.buscaClientes(nome, cpf);
    }//GEN-LAST:event_btnBuscaActionPerformed

    private void btnReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservaActionPerformed
        String clienteSelecionado = lstBuscaUsuario.getSelectedValue();

        if (clienteSelecionado == null || !clienteSelecionado.contains("ID: ")) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um cliente para realizar a reserva.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String id = clienteSelecionado.split("ID: ")[1].split(" - Nome: ")[0].trim();
            int clienteId = Integer.parseInt(id);

            Cliente cliente = clienteController.findById(clienteId);

            if (cliente == null) {
                JOptionPane.showMessageDialog(this, "Cliente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            DlgBuscaPet telaBuscaPet = new DlgBuscaPet(new javax.swing.JFrame(), true);
            telaBuscaPet.setClienteAtual(cliente);
            telaBuscaPet.setVisible(true);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Formato de ID inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao selecionar o cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnReservaActionPerformed

    private void btnEditarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarClienteActionPerformed

        String clienteSelecionado = lstBuscaUsuario.getSelectedValue();

        if (clienteSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um cliente.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!clienteSelecionado.contains("ID: ")) {
            JOptionPane.showMessageDialog(this, "Formato inválido da entrada selecionada.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String id = clienteSelecionado.split("ID: ")[1].toString().split(" - Nome: ")[0];
        Cliente cliente = clienteController.findById(Integer.parseInt(id));

        if (cliente == null) {
            JOptionPane.showMessageDialog(this, "Cliente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DlgCadCliente dlgCadCliente = new DlgCadCliente(new javax.swing.JFrame(), true);
        dlgCadCliente.setCliente(cliente);
        dlgCadCliente.setVisible(true);

        this.buscaClientes();

    }//GEN-LAST:event_btnEditarClienteActionPerformed

    private void btnNovoPetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoPetActionPerformed

        String clienteSelecionado = lstBuscaUsuario.getSelectedValue();

        if (clienteSelecionado == null || !clienteSelecionado.contains("ID: ")) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um cliente para cadastrar um pet.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String[] partesCliente = clienteSelecionado.split("ID: ")[1].split(" - Nome: ");
            String id = partesCliente[0].trim();
            String cpf = clienteSelecionado.split(" - CPF: ")[1].trim();

            if (id.isEmpty() || cpf.isEmpty()) {
                throw new IllegalArgumentException("Dados do cliente inválidos.");
            }

            DlgCadPet dlgCadPet = new DlgCadPet(new javax.swing.JFrame(), true);
            dlgCadPet.setResponsavelId(Integer.parseInt(id));
            dlgCadPet.setCPFResponsavel(cpf);
            dlgCadPet.setVisible(true);

        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnNovoPetActionPerformed

    private void lstBuscaUsuarioAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_lstBuscaUsuarioAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_lstBuscaUsuarioAncestorAdded

     public void adicionarMascaraNosCampos() {
        try {
            MaskFormatter maskCpf = new MaskFormatter("###.###.###-##");
            maskCpf.install(buscaCpf);

        } catch (ParseException ex) {
            Logger.getLogger(DlgCadCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBusca;
    private javax.swing.JButton btnEditarCliente;
    private javax.swing.JButton btnNovoPet;
    private javax.swing.JButton btnReserva;
    private javax.swing.JFormattedTextField buscaCpf;
    private javax.swing.JTextField buscaNome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCPF;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JList<String> lstBuscaUsuario;
    // End of variables declaration//GEN-END:variables
}
