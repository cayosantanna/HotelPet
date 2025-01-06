/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.mycompany.gui;

import controller.ClienteController;
import controller.FuncionarioController;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;
import model.Cliente;
import model.Funcionario;
import model.valid.ValidateCliente;

/**
 *
 * @author cayo
 */
public class DlgCadFuncionario extends javax.swing.JDialog {

    private final ClienteController clienteController;
    private final FuncionarioController funcionarioController;
    private Funcionario funcionarioEdicao;
    private String cpfRhLogado;
    private Funcionario funcionarioEmEdicao;

    public DlgCadFuncionario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.clienteController = new ClienteController();
        this.funcionarioController = new FuncionarioController();
        this.adicionarMascaraNosCampos();
    }

    public DlgCadFuncionario(java.awt.Frame parent, boolean modal, Funcionario funcionario, String cpfRhLogado) {
        super(parent, modal);
        initComponents();
        this.clienteController = new ClienteController();
        this.funcionarioController = new FuncionarioController();
        this.cpfRhLogado = cpfRhLogado;
        this.adicionarMascaraNosCampos();
        this.funcionarioEdicao = funcionario;
        if (funcionarioEdicao != null) {
            inputNome.setText(funcionarioEdicao.getNome());
            inputCpf.setText(funcionarioEdicao.getCpf());
            inputEmail.setText(funcionarioEdicao.getEmail());
            inputTelefone.setText(funcionarioEdicao.getTelefone());
            inputSenha.setText(funcionarioEdicao.getSenha());
            comboboxCargo.setSelectedItem(funcionarioEdicao.getCargo());
            setTitle("Edição de Funcionário");
            inputCpf.setEditable(false); // Impede edição do CPF
        }
    }

    public DlgCadFuncionario(java.awt.Frame parent, boolean modal, String cpfRhLogado) {
        super(parent, modal);
        initComponents();
        this.clienteController = new ClienteController();
        this.funcionarioController = new FuncionarioController();
        this.cpfRhLogado = cpfRhLogado;
        this.adicionarMascaraNosCampos();
        // Define edição como nula, pois é novo cadastro
        funcionarioEdicao = null;
        setTitle("Novo Funcionário");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        inputNome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        inputEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnConfirmar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        inputSenha = new javax.swing.JPasswordField();
        inputTelefone = new javax.swing.JFormattedTextField();
        inputCpf = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        comboboxCargo = new javax.swing.JComboBox<>();
        lblTitulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Nome");

        jLabel2.setText("CPF");

        jLabel4.setText("Email");

        jLabel5.setText("Telefone");

        btnConfirmar.setText("Confirmar");
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel7.setText("Senha");

        jLabel8.setText("Cargo:");

        comboboxCargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Recepcionista","Gestor de RH"}));
        comboboxCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxCargoActionPerformed(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Liberation Sans", 1, 36)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Cadastro Funcionário");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(279, 279, 279)
                        .addComponent(jLabel3))
                    .addComponent(jLabel4)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addComponent(btnConfirmar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(comboboxCargo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(inputEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                        .addComponent(inputNome, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnCancelar)
                        .addComponent(jLabel5)
                        .addComponent(jLabel2)
                        .addComponent(inputTelefone)
                        .addComponent(inputCpf, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                        .addComponent(jLabel7))
                    .addComponent(inputSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(78, Short.MAX_VALUE))
            .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboboxCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirmar)
                    .addComponent(btnCancelar))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {
    try {
        String nome = inputNome.getText().trim();
        String cpf = inputCpf.getText().replaceAll("[^\\d]", "");
        String email = inputEmail.getText().trim();
        String telefone = inputTelefone.getText().replaceAll("[^\\d]", "");
        String senha = new String(inputSenha.getPassword());
        String cargo = comboboxCargo.getSelectedItem().toString();

        // Valida os campos
        ValidateCliente.validateFuncionario(cpf, email, telefone, senha);

        if (funcionarioEdicao != null) {
            // Não altera o CPF na edição
            funcionarioEdicao.setNome(nome);
            funcionarioEdicao.setEmail(email);
            funcionarioEdicao.setTelefone(telefone);
            funcionarioEdicao.setSenha(senha);
            // Só altera o cargo se não for RH
            if (!funcionarioEdicao.getCargo().equalsIgnoreCase("Gestor de RH")) {
                funcionarioEdicao.setCargo(cargo);
            }
            funcionarioController.editFuncionario(funcionarioEdicao, cpfRhLogado);
            JOptionPane.showMessageDialog(this, "Funcionário atualizado com sucesso!");
        } else {
            // Novo funcionário
            Funcionario funcionario = new Funcionario(nome, cpf, email, telefone, senha, cargo, true);
            funcionarioController.createFuncionario(funcionario, cpfRhLogado);
            JOptionPane.showMessageDialog(this, "Funcionário cadastrado com sucesso!");
        }

        this.dispose();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }
}


    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void comboboxCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxCargoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboboxCargoActionPerformed

    public void adicionarMascaraNosCampos() {
        try {
            MaskFormatter maskCpf = new MaskFormatter("###.###.###-##");
            maskCpf.install(inputCpf);

            MaskFormatter maskTelefone = new MaskFormatter("(##) #####-####");
            maskTelefone.install(inputTelefone);

        } catch (ParseException ex) {
            Logger.getLogger(DlgCadFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setFuncionarioParaEdicao(Funcionario funcionario, String cpfRhLogado) {
        inputNome.setText(funcionario.getNome());
        inputCpf.setText(funcionario.getCpf());
        inputEmail.setText(funcionario.getEmail());
        inputTelefone.setText(funcionario.getTelefone());
        inputSenha.setText(funcionario.getSenha());
        comboboxCargo.setSelectedItem(funcionario.getCargo());
        
        // Desabilita a edição do CPF
        inputCpf.setEditable(false);
        
        // Se o funcionário for RH, desabilita a mudança de cargo
        if (funcionario.getCargo().equalsIgnoreCase("Gestor de RH")) {
            comboboxCargo.setEnabled(false);
        }
        
        // Guarda referência do funcionário sendo editado
        this.funcionarioEdicao = funcionario;
        this.cpfRhLogado = cpfRhLogado;
    }
     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JComboBox<String> comboboxCargo;
    private javax.swing.JFormattedTextField inputCpf;
    private javax.swing.JTextField inputEmail;
    private javax.swing.JTextField inputNome;
    private javax.swing.JPasswordField inputSenha;
    private javax.swing.JFormattedTextField inputTelefone;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables
}
