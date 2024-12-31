package com.mycompany.gui;

import controller.PetController;
import java.awt.Frame;
import java.text.DateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import model.Pet;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
public class DlgCadPet extends javax.swing.JDialog {

    private PetController petController;
    private String cpfResponsavel;
    private Integer responsavelId;

    public DlgCadPet(Frame parent, boolean modal, String cpfResponsavel, Integer responsavelId, Pet pet) {
    super(parent, modal);
    this.cpfResponsavel = cpfResponsavel;
    this.responsavelId = responsavelId;
    petController = new PetController();
    initComponents();
    limparCampos();
    edtCPFUsuario.setText(cpfResponsavel);
    edtCPFUsuario.setEnabled(false);

    // Preencher os campos com os dados do pet
    edtNome.setText(pet.getNome());
    edtDataNascimento.setText(pet.getDatanascimento());
    edtRaca.setText(pet.getRaca());
    txtAreaCaracteristicasFisicas.setText(pet.getCaracteristicasFisicas());
    txtHistoricoDoencas.setText(pet.getHistoricoDoencas());
    txtAreaMedicacoes.setText(pet.getMedicacoes());
    comboBoxEspecie.setSelectedItem(pet.getEspecie());
    comboBoxPorte.setSelectedItem(pet.getPorte());
    comboBoxSexo.setSelectedItem(pet.getSexo());
}
    private DlgBuscaPet dlgBuscaPet;

    public void setDlgBuscaPet(DlgBuscaPet dlgBuscaPet) {
    this.dlgBuscaPet = dlgBuscaPet;
    }

    public void limparCampos() {
        edtNome.setText("");
        edtDataNascimento.setText("");
        edtRaca.setText("");
        txtAreaCaracteristicasFisicas.setText("");
        txtHistoricoDoencas.setText("");
        txtAreaMedicacoes.setText("");
        comboBoxEspecie.setSelectedIndex(0);
        comboBoxPorte.setSelectedIndex(0);
        comboBoxSexo.setSelectedIndex(0);
    }

    private void validarCampos() throws IllegalArgumentException {
        if (edtNome.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("O campo Nome é obrigatório.");
        }
        if (edtDataNascimento.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("O campo Data de Nascimento é obrigatório.");
        }
        if (edtRaca.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("O campo Raça é obrigatório.");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        edtNome = new javax.swing.JTextField();
        btnConfirma = new javax.swing.JButton();
        lblDataNascimento = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        edtDataNascimento = new javax.swing.JTextField();
        lblCarateristicasFisicas = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        lblResposaveis = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaResponsaveis = new javax.swing.JTextArea();
        lblDoencasAlergias = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAreaCaracteristicasFisicas = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtHistoricoDoencas = new javax.swing.JTextArea();
        lblMedicacoes = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtAreaMedicacoes = new javax.swing.JTextArea();
        lblEspecie = new javax.swing.JLabel();
        comboBoxEspecie = new javax.swing.JComboBox<>();
        lblSexo = new javax.swing.JLabel();
        comboBoxSexo = new javax.swing.JComboBox<>();
        lblPorte = new javax.swing.JLabel();
        comboBoxPorte = new javax.swing.JComboBox<>();
        lblRaca = new javax.swing.JLabel();
        edtRaca = new javax.swing.JTextField();
        edtCPFUsuario = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                formInputMethodTextChanged(evt);
            }
        });

        btnConfirma.setText("Confirma");
        btnConfirma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmaActionPerformed(evt);
            }
        });

        lblDataNascimento.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        lblDataNascimento.setText("Dada de Nascimento:");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        edtDataNascimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtDataNascimentoActionPerformed(evt);
            }
        });

        lblCarateristicasFisicas.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        lblCarateristicasFisicas.setText("Caracteristicas Fisicas (Cor, manchas e etc):");

        lblTitulo.setFont(new java.awt.Font("Liberation Sans", 1, 36)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Cadastro Pet");

        lblResposaveis.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        lblResposaveis.setText("Resposáveis:");

        lblNome.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        lblNome.setText("Nome:");

        txtAreaResponsaveis.setColumns(20);
        txtAreaResponsaveis.setRows(5);
        jScrollPane1.setViewportView(txtAreaResponsaveis);

        lblDoencasAlergias.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        lblDoencasAlergias.setText("Histórico de Doenças ou Alergias:");

        txtAreaCaracteristicasFisicas.setColumns(20);
        txtAreaCaracteristicasFisicas.setRows(5);
        jScrollPane3.setViewportView(txtAreaCaracteristicasFisicas);

        txtHistoricoDoencas.setColumns(20);
        txtHistoricoDoencas.setRows(5);
        jScrollPane2.setViewportView(txtHistoricoDoencas);

        lblMedicacoes.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        lblMedicacoes.setText("Medicações Necesárias:");

        txtAreaMedicacoes.setColumns(20);
        txtAreaMedicacoes.setRows(5);
        jScrollPane4.setViewportView(txtAreaMedicacoes);

        lblEspecie.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        lblEspecie.setText("Espécie:");

        comboBoxEspecie.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cachorro", "Gato" }));
        comboBoxEspecie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxEspecieActionPerformed(evt);
            }
        });

        lblSexo.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        lblSexo.setText("Sexo:");

        comboBoxSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Feminino", "Masculino" }));

        lblPorte.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        lblPorte.setText("Porte:");

        comboBoxPorte.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pequeno", "Médio", "Grande" }));

        lblRaca.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        lblRaca.setText("Raça:");

        edtCPFUsuario.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        edtCPFUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtCPFUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(60, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnConfirma)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblDoencasAlergias)
                                    .addComponent(lblNome)
                                    .addComponent(lblCarateristicasFisicas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(edtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane3))
                                .addComponent(edtCPFUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMedicacoes)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(50, 50, 50))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblDataNascimento)
                                        .addComponent(edtDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnCancelar)
                                        .addComponent(lblResposaveis)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addContainerGap()))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(comboBoxEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboBoxPorte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblPorte))
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSexo)
                                    .addComponent(comboBoxSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblEspecie))
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblRaca)
                            .addComponent(edtRaca, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(edtCPFUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome)
                    .addComponent(lblDataNascimento))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(edtDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCarateristicasFisicas)
                    .addComponent(lblMedicacoes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDoencasAlergias)
                    .addComponent(lblResposaveis))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEspecie)
                            .addComponent(lblPorte)
                            .addComponent(lblSexo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboBoxEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxPorte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(lblRaca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtRaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirma)
                    .addComponent(btnCancelar))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfirmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmaActionPerformed
                                           
    try {
        validarCampos();

        // Coleta os dados preenchidos no formulário
        String nome = edtNome.getText().trim();
        String dataNascimento = edtDataNascimento.getText().trim();
        String especie = comboBoxEspecie.getSelectedItem().toString();
        String raca = edtRaca.getText().trim();
        String porte = comboBoxPorte.getSelectedItem().toString();
        String sexo = comboBoxSexo.getSelectedItem().toString();
        String caracteristicasFisicas = txtAreaCaracteristicasFisicas.getText().trim();
        String historicoDoencas = txtHistoricoDoencas.getText().trim();
        String medicacoes = txtAreaMedicacoes.getText().trim();
        boolean ativo = true;  // Pet está ativo

        // Criação do objeto Pet com os dados fornecidos
        Pet pet = new Pet(0, nome, dataNascimento, especie, raca, porte, sexo, caracteristicasFisicas, historicoDoencas, medicacoes, ativo);

        // Cadastrar o pet usando o petController
        petController.cadastrarPet(pet, this.responsavelId);

        // Exibe a mensagem de sucesso
        JOptionPane.showMessageDialog(
                this,
                "Pet cadastrado com sucesso!\n\n"
                + "Nome: " + nome
                + "\nEspécie: " + especie
                + "\nRaça: " + raca
                + "\nResponsável (CPF): " + cpfResponsavel,
                "Cadastro de Pet",
                JOptionPane.INFORMATION_MESSAGE
        );

        // Fecha a janela de cadastro
        this.dispose();

        // Atualiza a lista de pets na janela de busca, se ela estiver aberta
        if (dlgBuscaPet != null) {
            dlgBuscaPet.atualizarListaDePets();  // Atualiza a lista de pets na tela de busca
        }

    } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(this, e.getMessage(), "Erro de Validação", JOptionPane.WARNING_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Erro ao cadastrar pet: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }

    }//GEN-LAST:event_btnConfirmaActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void comboBoxEspecieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxEspecieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxEspecieActionPerformed

    private void edtDataNascimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtDataNascimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtDataNascimentoActionPerformed

    private void edtCPFUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtCPFUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtCPFUsuarioActionPerformed

    private void formInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_formInputMethodTextChanged
        edtDataNascimento.setText(edtDataNascimento.getText().replaceAll("(\\d{2})(\\d{2})(\\d{4})", "$1/$2/$3"));
    }//GEN-LAST:event_formInputMethodTextChanged
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirma;
    private javax.swing.JComboBox<String> comboBoxEspecie;
    private javax.swing.JComboBox<String> comboBoxPorte;
    private javax.swing.JComboBox<String> comboBoxSexo;
    private javax.swing.JTextField edtCPFUsuario;
    private javax.swing.JTextField edtDataNascimento;
    private javax.swing.JTextField edtNome;
    private javax.swing.JTextField edtRaca;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblCarateristicasFisicas;
    private javax.swing.JLabel lblDataNascimento;
    private javax.swing.JLabel lblDoencasAlergias;
    private javax.swing.JLabel lblEspecie;
    private javax.swing.JLabel lblMedicacoes;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblPorte;
    private javax.swing.JLabel lblRaca;
    private javax.swing.JLabel lblResposaveis;
    private javax.swing.JLabel lblSexo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextArea txtAreaCaracteristicasFisicas;
    private javax.swing.JTextArea txtAreaMedicacoes;
    private javax.swing.JTextArea txtAreaResponsaveis;
    private javax.swing.JTextArea txtHistoricoDoencas;
    // End of variables declaration//GEN-END:variables

    private void setStatus(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
