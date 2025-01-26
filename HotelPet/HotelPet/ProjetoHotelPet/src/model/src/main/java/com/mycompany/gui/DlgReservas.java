/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.mycompany.gui;

import controller.ClienteController;
import controller.PetController;
import controller.ReservaController;
import java.awt.Frame;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;
import model.Cliente;
import model.Pet;
import model.Reserva;

/**
 * Tela de reservas para realizar uma nova reserva.
 */
public class DlgReservas extends javax.swing.JDialog {
    private Cliente cliente;
    private Pet pet;
    private ClienteController clienteController;
    private PetController petController;
    private ReservaController reservaController;

    private static final double VALOR_DIARIA = 75.0;
    private static final int LIMITE_DIAS_SEM_CHECKOUT = 20;

    public DlgReservas(Frame parent, boolean modal, int clienteId, int petId) {
        super(parent, modal);
        this.clienteController = new ClienteController();
        this.petController = new PetController();
        this.reservaController = new ReservaController();
        this.cliente = this.clienteController.findById(clienteId);
        this.pet = this.petController.findById(petId);
        initComponents();
        edtCPFCliente.setText(cliente.getCpf());
        edtNomePet.setText(pet.getNome());
        edtCPFCliente.setEditable(false);
        edtNomePet.setEditable(false);
        edtDataReserva.setEditable(false);
        edtDataReserva.setText(obterDataAtual());

   
        adicionarMascaras();

        // Verificar alertas de estadias prolongadas
        verificarReservasSemCheckout();
        btnSalvar.setEnabled(false); // Inicia com botão salvar desabilitado
        edtValorTotal.setEditable(false); // Impede edição manual
    
        try {
            MaskFormatter mf = new MaskFormatter("##/##/####");
            mf.setPlaceholderCharacter('_');
            mf.install(edtCheckIn1);
            mf.install(edtCheckOut);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        btnSalvar.setEnabled(false);
    
        // Desabilitar salvar ao alterar campos
        checkBoxBanho.addActionListener(e -> btnSalvar.setEnabled(false));
        checkBoxTosa.addActionListener(e -> btnSalvar.setEnabled(false));
        checkBoxPasseio.addActionListener(e -> btnSalvar.setEnabled(false));
        checkBoxAlimentacaoEspecial.addActionListener(e -> btnSalvar.setEnabled(false));
        edtCheckIn1.addCaretListener(e -> btnSalvar.setEnabled(false));
        edtCheckOut.addCaretListener(e -> btnSalvar.setEnabled(false));
        txtServicosExtras.addCaretListener(e -> btnSalvar.setEnabled(false));
    }

    

    private String obterDataAtual() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(new Date());
    }
    private void adicionarMascaras() {
    try {
        MaskFormatter mask = new MaskFormatter("##/##/####");
        mask.setPlaceholderCharacter('_');

        edtCheckIn1 = new javax.swing.JFormattedTextField(mask);
        edtCheckOut = new javax.swing.JFormattedTextField(mask);

    } catch (ParseException ex) {
        JOptionPane.showMessageDialog(this, "Erro ao configurar máscaras: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }
}

    private void atualizarValorTotal() {
        double valorTotal = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        try {
            Date checkIn = sdf.parse(edtCheckIn1.getText());
            Date checkOut = edtCheckOut.getText().isEmpty() ? new Date() : sdf.parse(edtCheckOut.getText());
            
            // Calcula dias de estadia
            long dias = (checkOut.getTime() - checkIn.getTime()) / (1000 * 60 * 60 * 24);
            if (edtCheckOut.getText().isEmpty() && dias > LIMITE_DIAS_SEM_CHECKOUT) {
                dias = LIMITE_DIAS_SEM_CHECKOUT;
            }

            // Valor base da diária
            valorTotal = dias * VALOR_DIARIA;
            
            // Adiciona serviços selecionados
            if (checkBoxBanho.isSelected()) valorTotal += 90.0;
            if (checkBoxTosa.isSelected()) valorTotal += 70.0;
            if (checkBoxPasseio.isSelected()) valorTotal += 60.0;
            if (checkBoxAlimentacaoEspecial.isSelected()) valorTotal += 100.0;
            
            // Adiciona serviços extras
            if (!txtServicosExtras.getText().isEmpty()) {
                valorTotal += Double.parseDouble(txtServicosExtras.getText());
            }
            
            edtValorTotal.setText(String.format("%.2f", valorTotal));
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao calcular valor: " + e.getMessage());
        }
    }

    private void verificarReservasSemCheckout() {
        List<Reserva> reservas = reservaController.verificarReservasSemCheckout();
        for (Reserva reserva : reservas) {
            if (reserva.getCheckIn() == null) continue;
            long diasSemCheckout = calcularDiasSemCheckout(reserva.getCheckIn());
            if (diasSemCheckout >= LIMITE_DIAS_SEM_CHECKOUT) {
                JOptionPane.showMessageDialog(this,
                        "Alerta: O pet " + reserva.getPet().getNome() +
                                " está hospedado há " + diasSemCheckout + " dias sem checkout.\n" +
                                "Cliente: " + reserva.getCliente().getNome() + "\n" +
                                "Telefone: " + reserva.getCliente().getTelefone(),
                        "Alerta",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private long calcularDiasSemCheckout(Date checkIn) {
        long diffInMillies = new Date().getTime() - checkIn.getTime();
        return diffInMillies / (1000 * 60 * 60 * 24);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        lblTituloRealizarReserva = new javax.swing.JLabel();
        lblCliente = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblServicosDisponiveis = new javax.swing.JLabel();
        checkBoxBanho = new javax.swing.JCheckBox();
        checkBoxTosa = new javax.swing.JCheckBox();
        checkBoxPasseio = new javax.swing.JCheckBox();
        checkBoxAlimentacaoEspecial = new javax.swing.JCheckBox();
        lblCheckIn = new javax.swing.JLabel();
        edtCPFCliente = new javax.swing.JTextField();
        lblCheckOut = new javax.swing.JLabel();
        edtCheckOut = new javax.swing.JFormattedTextField();
        lblValorTotal = new javax.swing.JLabel();
        edtValorTotal = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblDataReserva = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        edtDataReserva = new javax.swing.JTextField();
        edtCheckIn1 = new javax.swing.JFormattedTextField();
        edtNomePet = new javax.swing.JTextField();
        lblServicosDisponiveis1 = new javax.swing.JLabel();
        txtServicosExtras = new javax.swing.JTextField();
        btnAtualizarValor = new javax.swing.JButton();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblTituloRealizarReserva.setFont(new java.awt.Font("Liberation Sans", 1, 36)); // NOI18N
        lblTituloRealizarReserva.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloRealizarReserva.setText("Realizar Reserva");
        lblTituloRealizarReserva.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblCliente.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        lblCliente.setText("Cliente:");

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel1.setText("Nome Pet:");

        lblServicosDisponiveis.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        lblServicosDisponiveis.setText("Serviços Disponíveis:");

        checkBoxBanho.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        checkBoxBanho.setText("Banho");
        checkBoxBanho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxBanhoActionPerformed(evt);
            }
        });

        checkBoxTosa.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        checkBoxTosa.setText("Tosa");
        checkBoxTosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxTosaActionPerformed(evt);
            }
        });

        checkBoxPasseio.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        checkBoxPasseio.setText("Passeio");
        checkBoxPasseio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxPasseioActionPerformed(evt);
            }
        });

        checkBoxAlimentacaoEspecial.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        checkBoxAlimentacaoEspecial.setText("Alimentação Especial");
        checkBoxAlimentacaoEspecial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxAlimentacaoEspecialActionPerformed(evt);
            }
        });

        lblCheckIn.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        lblCheckIn.setText("Check-in:");

        lblCheckOut.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        lblCheckOut.setText("Check-out:");

        lblValorTotal.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        lblValorTotal.setText("Valor Total a Pagar:");

        edtValorTotal.setEditable(false);

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");

        lblDataReserva.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        lblDataReserva.setText("Data da Realização da Reserva:");

        lblServicosDisponiveis1.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        lblServicosDisponiveis1.setText("Serviços extras:");

        txtServicosExtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtServicosExtrasActionPerformed(evt);
            }
        });

        btnAtualizarValor.setText("Atualizar");
        btnAtualizarValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarValorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCliente)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(67, 67, 67)
                            .addComponent(checkBoxBanho)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(checkBoxTosa)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(checkBoxPasseio)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(checkBoxAlimentacaoEspecial))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(67, 67, 67)
                            .addComponent(lblServicosDisponiveis))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(67, 67, 67)
                            .addComponent(lblCheckIn))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(67, 67, 67)
                            .addComponent(edtCPFCliente))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(67, 67, 67)
                            .addComponent(lblCheckOut))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(67, 67, 67)
                            .addComponent(lblValorTotal))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(133, 133, 133)
                            .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(74, 74, 74)
                            .addComponent(btnCancelar))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel3))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(67, 67, 67)
                            .addComponent(edtCheckIn1))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(67, 67, 67)
                            .addComponent(edtNomePet))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(67, 67, 67)
                            .addComponent(edtCheckOut))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(67, 67, 67)
                            .addComponent(lblServicosDisponiveis1))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(67, 67, 67)
                            .addComponent(txtServicosExtras))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(67, 67, 67)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnAtualizarValor)
                                .addComponent(edtValorTotal))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDataReserva)
                            .addComponent(edtDataReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 67, Short.MAX_VALUE))
            .addComponent(lblTituloRealizarReserva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblTituloRealizarReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(lblCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edtCPFCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edtNomePet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(lblServicosDisponiveis)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkBoxBanho)
                    .addComponent(checkBoxTosa)
                    .addComponent(checkBoxPasseio)
                    .addComponent(checkBoxAlimentacaoEspecial))
                .addGap(18, 18, 18)
                .addComponent(lblServicosDisponiveis1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtServicosExtras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblCheckIn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edtCheckIn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblCheckOut)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edtCheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblDataReserva)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edtDataReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(lblValorTotal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAtualizarValor)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void checkBoxBanhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxBanhoActionPerformed
        atualizarValorTotal();
        btnSalvar.setEnabled(false);
    }//GEN-LAST:event_checkBoxBanhoActionPerformed

    private void checkBoxTosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxTosaActionPerformed
        atualizarValorTotal();
        btnSalvar.setEnabled(false);
    }//GEN-LAST:event_checkBoxTosaActionPerformed

    private void checkBoxAlimentacaoEspecialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxAlimentacaoEspecialActionPerformed
       atualizarValorTotal();
       btnSalvar.setEnabled(false);
    }//GEN-LAST:event_checkBoxAlimentacaoEspecialActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
    try {
        // Verificar se o campo Check-In está vazio
        if (edtCheckIn1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha a data de Check-In.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar o formato da data de Check-In
        if (!validarFormatoData(edtCheckIn1.getText())) {
            JOptionPane.showMessageDialog(this, "Data de Check-In inválida. Use o formato dd/MM/yyyy.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar o formato da data de Check-Out, se preenchido
        if (!edtCheckOut.getText().isEmpty() && !validarFormatoData(edtCheckOut.getText())) {
            JOptionPane.showMessageDialog(this, "Data de Check-Out inválida. Use o formato dd/MM/yyyy.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obter e validar as datas
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); // Força validação estrita de datas
        Date dataReserva = sdf.parse(edtDataReserva.getText());
        Date checkIn = sdf.parse(edtCheckIn1.getText());
        Date checkOut = edtCheckOut.getText().isEmpty() ? null : sdf.parse(edtCheckOut.getText());

        // Verificar se Check-In é anterior à data de reserva
        if (checkIn.before(dataReserva)) {
            JOptionPane.showMessageDialog(this, "A data de Check-In não pode ser anterior à data de reserva.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificar se Check-Out é anterior à data de Check-In
        if (checkOut != null && checkOut.before(checkIn)) {
            JOptionPane.showMessageDialog(this, "A data de Check-Out não pode ser anterior à data de Check-In.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Calcular o valor total da reserva
        double valorTotal = Double.parseDouble(edtValorTotal.getText().replace(",", "."));

        // Criar objeto Reserva e preencher os dados
        Reserva reserva = new Reserva();
        reserva.setCliente(cliente);
        reserva.setPet(pet);
        reserva.setCheckIn(checkIn);
        reserva.setCheckOut(checkOut);
        reserva.setValorTotal(valorTotal);
        reserva.setServicoBanho(checkBoxBanho.isSelected());
        reserva.setServicoTosa(checkBoxTosa.isSelected());
        reserva.setServicoPasseio(checkBoxPasseio.isSelected());
        reserva.setServicoAlimentacaoEspecial(checkBoxAlimentacaoEspecial.isSelected());
        reserva.setDescricaoServicosExtras(txtServicosExtras.getText());

        // Salvar reserva no banco de dados
        reservaController.salvarReservaComValidacao(reserva);

        // Exibir mensagem de sucesso
        JOptionPane.showMessageDialog(this, "Reserva salva com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        // Abrir tela de pagamento
        DlgTelaPagamento telaPagamento = new DlgTelaPagamento(new javax.swing.JFrame(), true, reserva);
        telaPagamento.setVisible(true);

        // Fechar a tela atual
        dispose();
    } catch (ParseException e) {
        JOptionPane.showMessageDialog(this, "Formato de data inválido. Use o formato dd/MM/yyyy.", "Erro", JOptionPane.ERROR_MESSAGE);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Valor total inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Ocorreu um erro inesperado: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void checkBoxPasseioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxPasseioActionPerformed
    atualizarValorTotal();
    btnSalvar.setEnabled(false);
    }//GEN-LAST:event_checkBoxPasseioActionPerformed

    private void txtServicosExtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtServicosExtrasActionPerformed
     atualizarValorTotal();
     btnSalvar.setEnabled(false);
    }//GEN-LAST:event_txtServicosExtrasActionPerformed

    private void btnAtualizarValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarValorActionPerformed
        try {
            if (edtCheckIn1.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Data de check-in é obrigatória!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!validarFormatoData(edtCheckIn1.getText())) {
                JOptionPane.showMessageDialog(this, "Formato de data inválido. Por favor, use o formato dd/MM/yyyy.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!edtCheckOut.getText().isEmpty() && !validarFormatoData(edtCheckOut.getText())) {
                JOptionPane.showMessageDialog(this, "Formato de data inválido. Por favor, use o formato dd/MM/yyyy.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            atualizarValorTotal();
            btnSalvar.setEnabled(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar valor: " + e.getMessage());
            btnSalvar.setEnabled(false);
        }
    }//GEN-LAST:event_btnAtualizarValorActionPerformed

    private boolean validarFormatoData(String data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); // Validação estrita
        try {
            sdf.parse(data); // Tenta analisar a data
            return true;
        } catch (ParseException e) {
            return false; // Retorna false se a data for inválida
        }
    }
    

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizarValor;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JCheckBox checkBoxAlimentacaoEspecial;
    private javax.swing.JCheckBox checkBoxBanho;
    private javax.swing.JCheckBox checkBoxPasseio;
    private javax.swing.JCheckBox checkBoxTosa;
    private javax.swing.JTextField edtCPFCliente;
    private javax.swing.JFormattedTextField edtCheckIn1;
    private javax.swing.JFormattedTextField edtCheckOut;
    private javax.swing.JTextField edtDataReserva;
    private javax.swing.JTextField edtNomePet;
    private javax.swing.JTextField edtValorTotal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblCheckIn;
    private javax.swing.JLabel lblCheckOut;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblDataReserva;
    private javax.swing.JLabel lblServicosDisponiveis;
    private javax.swing.JLabel lblServicosDisponiveis1;
    private javax.swing.JLabel lblTituloRealizarReserva;
    private javax.swing.JLabel lblValorTotal;
    private javax.swing.JTextField txtServicosExtras;
    // End of variables declaration//GEN-END:variables
}
