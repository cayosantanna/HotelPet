/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.gui;

import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import model.Funcionario;
import controller.FuncionarioController;
import javax.swing.JScrollPane;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import java.sql.SQLException;

/**
 *
 * @author famil
 */
public class FrfuncionarioRH extends javax.swing.JFrame {

    
private final FuncionarioController funcionarioController;
    private final String cpfRhLogado;

    
    public FrfuncionarioRH(String cpfRhLogado) {
        initComponents();
        this.funcionarioController = new FuncionarioController();
        this.cpfRhLogado = cpfRhLogado;
        setTitle("Gerenciamento de Funcionários - RH");
        setLocationRelativeTo(null);
        
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        recarregarListaFuncionarios();
        btnEditarFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarFuncionarioActionPerformed(evt);
            }
        });
    }

    private void recarregarListaFuncionarios() {
        try {
            List<Funcionario> funcionarios = funcionarioController.getAllFuncionarios();
            DefaultListModel<String> listModel = new DefaultListModel<>();
            for (Funcionario funcionario : funcionarios) {
                if (funcionario != null && funcionario.isAtivo()) { // Verifica se funcionário não é nulo e está ativo
                    listModel.addElement(String.format("ID: %d - Nome: %s - CPF: %s - Cargo: %s",
                        funcionario.getId(), 
                        funcionario.getNome(), 
                        funcionario.getCpf(), 
                        funcionario.getCargo()));
                }
            }
            lstFuncionarios.setModel(listModel);
            // Força atualização visual
            lstFuncionarios.updateUI();
        } catch (Exception e) {
            e.printStackTrace(); // Log do erro completo
            JOptionPane.showMessageDialog(this, 
                "Erro ao atualizar lista: " + e.getMessage(),
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void setFuncionario(Boolean funcionario) {
        this.btnDemissão.setEnabled(funcionario);
        this.btnEditarFuncionario.setEnabled(funcionario);
        this.btnNovoFuncionario.setEnabled(funcionario);
    }

    private void atualizarListaFuncionarios(List<Funcionario> funcionarios) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Funcionario funcionario : funcionarios) {
            if (!funcionario.isAtivo()) continue; // Ignorar funcionários inativos
            listModel.addElement(String.format("ID: %d - Nome: %s - CPF: %s - Cargo: %s",
                    funcionario.getId(), funcionario.getNome(), funcionario.getCpf(), funcionario.getCargo()));
        }
        lstFuncionarios.setModel(listModel);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lstFuncionarios = new javax.swing.JList<>();
        txtNomeFuncionario = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtCPFfuncionario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        btnNovoFuncionario = new javax.swing.JButton();
        btnEditarFuncionario = new javax.swing.JButton();
        btnDemissão = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        btnSair = new javax.swing.JButton();
        btnContatos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lstFuncionarios.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {  };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lstFuncionarios);

        jLabel1.setText("Nome do Funcionario:");

        jLabel2.setText("CPF:");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnNovoFuncionario.setText("Novo Funcionário");
        btnNovoFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoFuncionarioActionPerformed(evt);
            }
        });

        btnEditarFuncionario.setText("Editar Funcionário");

        btnDemissão.setText("Demissão");
        btnDemissão.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDemissãoActionPerformed(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Liberation Sans", 1, 36)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Menu de Gerenciamento");

        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        btnContatos.setText("Contatos");
        btnContatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContatosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSair)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBuscar)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNomeFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addGap(46, 46, 46)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCPFfuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnNovoFuncionario)
                                    .addGap(50, 50, 50)
                                    .addComponent(btnEditarFuncionario)
                                    .addGap(50, 50, 50)
                                    .addComponent(btnDemissão)
                                    .addGap(50, 50, 50)
                                    .addComponent(btnContatos))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(48, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovoFuncionario)
                    .addComponent(btnEditarFuncionario)
                    .addComponent(btnDemissão)
                    .addComponent(btnContatos))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomeFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCPFfuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscar)
                .addGap(23, 23, 23)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnSair)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDemissãoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDemissãoActionPerformed
        String selected = lstFuncionarios.getSelectedValue();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Selecione um funcionário para demitir.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String cpf = selected.split("CPF: ")[1].split(" -")[0];
        if (cpf.equals(cpfRhLogado)) {
            JOptionPane.showMessageDialog(this, "Você não pode demitir a si mesmo.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Tem certeza que deseja demitir este funcionário?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                funcionarioController.demitirFuncionario(cpf, cpfRhLogado);
                JOptionPane.showMessageDialog(this, "Funcionário demitido com sucesso!");
                
                // Limpar seleção e atualizar lista
                lstFuncionarios.clearSelection();
                DefaultListModel<String> model = (DefaultListModel<String>) lstFuncionarios.getModel();
                model.clear();
                recarregarListaFuncionarios();
                
                // Força atualização visual
                lstFuncionarios.revalidate();
                lstFuncionarios.repaint();
                
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, 
                    "Erro ao demitir funcionário: " + e.getMessage(), 
                    "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnDemissãoActionPerformed

    private void btnNovoFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoFuncionarioActionPerformed
        DlgCadFuncionario dialog = new DlgCadFuncionario(this, true, cpfRhLogado);
        dialog.setVisible(true);
        recarregarListaFuncionarios();
    }//GEN-LAST:event_btnNovoFuncionarioActionPerformed

    private void btnEditarFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarFuncionarioActionPerformed
        String selected = lstFuncionarios.getSelectedValue();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Selecione um funcionário para editar.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String cpf = selected.split("CPF: ")[1].split(" -")[0];
        if (cpf.equals(cpfRhLogado)) {
            JOptionPane.showMessageDialog(this, "Você não pode editar seus próprios dados.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Funcionario funcionario = funcionarioController.findByCpf(cpf);
            if (funcionario != null) {
                DlgCadFuncionario dialog = new DlgCadFuncionario(this, true);
                dialog.setFuncionarioParaEdicao(funcionario, cpfRhLogado);
                dialog.setVisible(true);
                recarregarListaFuncionarios(); // Atualiza a lista após edição
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Erro ao editar funcionário: " + e.getMessage(),
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarFuncionarioActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        try {
            String nome = txtNomeFuncionario.getText().trim();
            String cpf = txtCPFfuncionario.getText().trim();

            List<Funcionario> funcionarios;
            if (nome.isEmpty() && cpf.isEmpty()) {
                funcionarios = funcionarioController.getAllFuncionarios();
            } else {
                funcionarios = funcionarioController.buscarFuncionarios(nome, cpf);
            }

            atualizarListaFuncionarios(funcionarios);
            
            if (funcionarios.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Nenhum funcionário encontrado",
                    "Busca",
                    JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Erro na busca: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        this.dispose();
        this.setVisible(false);
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnContatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContatosActionPerformed
     DlgContato telaCtt = new DlgContato(new javax.swing.JFrame(), true);
      telaCtt.setVisible(true);   
    }//GEN-LAST:event_btnContatosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnContatos;
    private javax.swing.JButton btnDemissão;
    private javax.swing.JButton btnEditarFuncionario;
    private javax.swing.JButton btnNovoFuncionario;
    private javax.swing.JButton btnSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JList<String> lstFuncionarios;
    private javax.swing.JTextField txtCPFfuncionario;
    private javax.swing.JTextField txtNomeFuncionario;
    // End of variables declaration//GEN-END:variables
}
