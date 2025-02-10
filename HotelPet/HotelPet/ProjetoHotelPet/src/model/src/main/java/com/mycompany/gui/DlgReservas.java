/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.mycompany.gui;

import controller.ClienteController;
import controller.PetController;
import controller.ReservaController;
import java.awt.Container;
import java.awt.Frame;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import model.Cliente;
import model.Pet;
import model.Reserva;
import javax.swing.text.MaskFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;

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
        try {
            this.clienteController = new ClienteController();
            this.petController = new PetController();
            this.reservaController = new ReservaController();

            // Validar cliente e pet
            this.cliente = this.clienteController.findById(clienteId);
            this.pet = this.petController.findById(petId);
            
            if (cliente == null || pet == null) {
                throw new Exception("Cliente ou Pet não encontrado");
            }
            
            initComponents();
            
            // Configurar campos
            configurarCampos();
            
            // Adicionar máscaras apenas para check-in e check-out
            adicionarMascarasDatas();
            
            // Adicionar validadores
            configurarValidadoresDatas();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao inicializar tela: " + e.getMessage());
            this.dispose();
        }
    }

    private void configurarCampos() {
        // Configurar campos não editáveis
        edtCPFCliente.setText(cliente.getCpf());
        edtNomePet.setText(pet.getNome());
        
        // Configurar data atual como não editável
        edtDataReserva.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        edtDataReserva.setEditable(false);
        edtDataReserva.setFocusable(false);
        edtDataReserva.setBackground(new java.awt.Color(240, 240, 240));
        
        // Outros campos não editáveis
        edtCPFCliente.setEditable(false);
        edtNomePet.setEditable(false);
        edtValorTotal.setEditable(false);
        
        btnSalvar.setEnabled(false);
    }

    private void adicionarMascarasDatas() {
        try {
            MaskFormatter maskData = new MaskFormatter("##/##/####");
            maskData.setPlaceholderCharacter('_');
            
            // Configurar campos formatados apenas para check-in e check-out
            JFormattedTextField txtCheckIn = new JFormattedTextField(maskData);
            JFormattedTextField txtCheckOut = new JFormattedTextField(maskData);
            
            // Configurar validação ao perder o foco
            txtCheckIn.setFocusLostBehavior(JFormattedTextField.COMMIT);
            txtCheckOut.setFocusLostBehavior(JFormattedTextField.COMMIT);
            
            // Manter propriedades visuais
            txtCheckIn.setBounds(edtCheckIn1.getBounds());
            txtCheckIn.setFont(edtCheckIn1.getFont());
            txtCheckOut.setBounds(edtCheckOut.getBounds());
            txtCheckOut.setFont(edtCheckOut.getFont());
            
            // Substituir campos antigos
            Container parent = edtCheckIn1.getParent();
            parent.remove(edtCheckIn1);
            parent.remove(edtCheckOut);
            parent.add(txtCheckIn);
            parent.add(txtCheckOut);
            
            edtCheckIn1 = txtCheckIn;
            edtCheckOut = txtCheckOut;
            
            // Garantir que a máscara esteja aplicada corretamente
            ((JFormattedTextField)edtCheckOut).setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(maskData));
            
            if (edtCheckOut instanceof javax.swing.JFormattedTextField) {
                ((javax.swing.JFormattedTextField) edtCheckOut)
                    .setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
            }
            
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao configurar máscaras: " + ex.getMessage());
        }
    }

    private void configurarValidadoresDatas() {
        edtCheckIn1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                validarDataCheckIn();
            }
        });
        
        edtCheckOut.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                validarDataCheckOut();
            }
        });
    }

    private Date removeTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return sdf.parse(sdf.format(date));
        } catch (ParseException e) {
            return date;
        }
    }

    private void validarDataCheckIn() {
        try {
            String dataStr = edtCheckIn1.getText().trim();
            if (dataStr.isEmpty() || !dataStr.matches("\\d{2}/\\d{2}/\\d{4}")) {
                JOptionPane.showMessageDialog(this, "Data de check-in é obrigatória e deve estar no formato dd/mm/aaaa");
                edtCheckIn1.setText("");
                return;
            }
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            
            // Pega a data atual da reserva
            Date dataReserva = sdf.parse(edtDataReserva.getText());
            Date checkIn = sdf.parse(dataStr);
            
            if (checkIn.before(dataReserva)) {
                JOptionPane.showMessageDialog(this, "Data de check-in não pode ser anterior à data atual");
                edtCheckIn1.setText("");
                btnSalvar.setEnabled(false);
                return;
            }
            
            // Limpa check-out se já estiver preenchido
            edtCheckOut.setText("");
            
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Data inválida");
            edtCheckIn1.setText("");
            btnSalvar.setEnabled(false);
        }
    }

    private void validarDataCheckOut() {
        try {
            String checkInStr = edtCheckIn1.getText().trim();
            String checkOutStr = edtCheckOut.getText().trim();
            
            if (checkOutStr.isEmpty()) {
                return; // Check-out é opcional
            }
            
            if (checkInStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha primeiro a data de check-in");
                edtCheckOut.setText("");
                return;
            }
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            
            Date checkIn = sdf.parse(checkInStr);
            Date checkOut = sdf.parse(checkOutStr);
            
            if (checkOut.before(checkIn) || checkOut.equals(checkIn)) {
                JOptionPane.showMessageDialog(this, "Data de check-out deve ser posterior ao check-in");
                edtCheckOut.setText("");
                btnSalvar.setEnabled(false);
            }
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Data inválida");
            edtCheckOut.setText("");
            btnSalvar.setEnabled(false);
        }
    }

    private String obterDataAtual() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(new Date());
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

    private void adicionarMascaraNosCampos() {
        try {
            MaskFormatter maskData = new MaskFormatter("##/##/####");
            maskData.setPlaceholderCharacter('_');
            
            // Aplicar máscara ao campo de check-in
            maskData.install((JFormattedTextField) edtCheckIn1);
            
            // Criar nova instância para o check-out
            MaskFormatter maskDataCheckout = new MaskFormatter("##/##/####");
            maskDataCheckout.setPlaceholderCharacter('_');
            maskDataCheckout.install((JFormattedTextField) edtCheckOut);
            
        } catch (ParseException ex) {
            Logger.getLogger(DlgReservas.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        edtCheckOut = new javax.swing.JTextField();
        lblValorTotal = new javax.swing.JLabel();
        edtValorTotal = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblDataReserva = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        edtDataReserva = new javax.swing.JTextField();
        edtCheckIn1 = new javax.swing.JTextField();
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

        edtValorTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtValorTotalActionPerformed(evt);
            }
        });

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        lblDataReserva.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        lblDataReserva.setText("Data da Realização da Reserva:");

        edtDataReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtDataReservaActionPerformed(evt);
            }
        });

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
            .addComponent(lblTituloRealizarReserva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(edtValorTotal)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtServicosExtras, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edtDataReserva, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edtCPFCliente)
                            .addComponent(edtNomePet, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCancelar)
                                .addGap(92, 92, 92))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblDataReserva, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblCliente, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(checkBoxBanho)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(checkBoxTosa)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(checkBoxPasseio)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(checkBoxAlimentacaoEspecial))
                                    .addComponent(lblServicosDisponiveis, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblServicosDisponiveis1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblCheckIn)
                                            .addComponent(edtCheckIn1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(120, 120, 120)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(edtCheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblCheckOut)))
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 6, Short.MAX_VALUE)))
                        .addGap(61, 61, 61))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAtualizarValor)
                            .addComponent(lblValorTotal))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblTituloRealizarReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(lblCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edtCPFCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edtNomePet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(lblServicosDisponiveis)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkBoxBanho)
                    .addComponent(checkBoxTosa)
                    .addComponent(checkBoxPasseio)
                    .addComponent(checkBoxAlimentacaoEspecial))
                .addGap(25, 25, 25)
                .addComponent(lblServicosDisponiveis1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtServicosExtras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCheckIn)
                    .addComponent(lblCheckOut))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edtCheckIn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtCheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(lblDataReserva)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(edtDataReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(lblValorTotal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAtualizarValor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void checkBoxBanhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxBanhoActionPerformed
        atualizarValorTotal();
    }//GEN-LAST:event_checkBoxBanhoActionPerformed

    private void checkBoxTosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxTosaActionPerformed
        atualizarValorTotal();
    }//GEN-LAST:event_checkBoxTosaActionPerformed

    private void checkBoxAlimentacaoEspecialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event checkBoxAlimentacaoEspecialActionPerformed
       atualizarValorTotal();
    }//GEN-LAST:event checkBoxAlimentacaoEspecialActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
    if (!validarFormatoData(edtCheckIn1.getText())) {
        JOptionPane.showMessageDialog(this, "Data de check-in inválida. Use dd/MM/yyyy.", "Erro", JOptionPane.ERROR_MESSAGE);
        return;
    }
    if (!edtCheckOut.getText().isEmpty() && !validarFormatoData(edtCheckOut.getText())) {
        JOptionPane.showMessageDialog(this, "Data de check-out inválida. Use dd/MM/yyyy.", "Erro", JOptionPane.ERROR_MESSAGE);
        return;
    }
    try {
        // Validar os campos
        if (edtCheckIn1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha a data de check-in.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Primeiro, obter os dados da reserva
        Reserva reserva = new Reserva();
        reserva.setCliente(cliente);
        reserva.setPet(pet);

        // Adicionar a data da reserva
        reserva.setDataReserva(new Date()); // Adiciona a data atual como data da reserva

        // Converter as strings para datas
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date checkIn = sdf.parse(edtCheckIn1.getText());
        Date checkOut = !edtCheckOut.getText().isEmpty() ? sdf.parse(edtCheckOut.getText()) : null;

        if (checkOut != null && checkIn.after(checkOut)) {
            JOptionPane.showMessageDialog(this, "A data de check-out não pode ser anterior ao check-in.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        reserva.setCheckIn(checkIn);
        reserva.setCheckOut(checkOut);

        // Validar e calcular o valor total
        String valorTotalStr = edtValorTotal.getText().replace(",", ".");
        double valorTotal = Double.parseDouble(valorTotalStr);
        reserva.setValorTotal(valorTotal);

        reserva.setServicoBanho(checkBoxBanho.isSelected());
        reserva.setServicoTosa(checkBoxTosa.isSelected());
        reserva.setServicoPasseio(checkBoxPasseio.isSelected());
        reserva.setServicoAlimentacaoEspecial(checkBoxAlimentacaoEspecial.isSelected());
        
        // Adicionar descrição dos serviços extras
        reserva.setDescricaoServicosExtras(txtServicosExtras.getText());

        // Verificar se já existe uma reserva para o mesmo pet no período especificado
        if (reservaController.existeReservaNoPeriodo(pet, checkIn, checkOut)) {
            JOptionPane.showMessageDialog(this, "Já existe uma reserva para este pet no período especificado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Salvar no banco de dados
        reservaController.salvarReservaComValidacao(reserva);

        // Reserva salva com sucesso
        JOptionPane.showMessageDialog(this, "Reserva salva com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        
        // Transferir dados para a tela de pagamento
        DlgTelaPagamento telaPagamento = new DlgTelaPagamento(new javax.swing.JFrame(), true, reserva);
        telaPagamento.setVisible(true);
        
        dispose(); // Fecha a janela de reserva

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Valor total inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
    } catch (ParseException e) {
        JOptionPane.showMessageDialog(this, "Formato de data inválido. Por favor, use o formato dd/MM/yyyy.", "Erro", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Ocorreu um erro inesperado: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void checkBoxPasseioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxPasseioActionPerformed
    atualizarValorTotal();
    }//GEN-LAST:event_checkBoxPasseioActionPerformed

    private void txtServicosExtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtServicosExtrasActionPerformed
     atualizarValorTotal();
     btnSalvar.setEnabled(false); // Bloqueia salvar ao alterar serviços extras
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

    private void edtDataReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtDataReservaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtDataReservaActionPerformed

    private void edtValorTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtValorTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtValorTotalActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
    this.dispose();
    this.setVisible(false);         // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

    private boolean validarFormatoData(String data) {
        return data.matches("\\d{2}/\\d{2}/\\d{4}");
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
    private javax.swing.JTextField edtCheckIn1;
    private javax.swing.JTextField edtCheckOut;
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
