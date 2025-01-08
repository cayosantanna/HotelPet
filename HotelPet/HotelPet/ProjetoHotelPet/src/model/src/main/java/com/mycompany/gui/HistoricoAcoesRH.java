package com.mycompany.gui;

import controller.FuncionarioController;

import javax.swing.*;
import java.util.List;

public class HistoricoAcoesRH extends javax.swing.JDialog {

    private final FuncionarioController funcionarioController;

    public HistoricoAcoesRH(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.funcionarioController = new FuncionarioController(); // Ajuste seu EntityManager aqui
        carregarHistorico();
    }

    private void carregarHistorico() {
        try {
            List<String> historico = funcionarioController.getHistoricoRH(); 
            // Ajustar para chamar o método que retorna formato (nome, cpf, cargo, ação, data/hora)
            // Exemplo: se existir um HistoricoRHController adicional, usar algo como:
            // List<String> historico = historicoRHController.obterTodasAcoesFormatadas();

            atualizarLista(historico);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Erro ao carregar histórico. " + e.getMessage(), 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
      private void atualizarLista(List<String> historico) {
        DefaultListModel<String> model = new DefaultListModel<>();
        historico.forEach(model::addElement);
        lstAtividadesRH.setModel(model);
        lstAtividadesRH.revalidate();
        lstAtividadesRH.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lstAtividadesRH = new javax.swing.JList<>();
        edtNome = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        edtCPF = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        btnFiltrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lstAtividadesRH.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lstAtividadesRH);

        jLabel1.setText("Digite o nome do gestor de RH:");

        edtCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtCPFActionPerformed(evt);
            }
        });

        jLabel2.setText("Digite o CPF do gestor de RH:");

        lblTitulo.setFont(new java.awt.Font("Liberation Sans", 1, 36)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Historico de atividade do RH");

        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFiltrar)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 736, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(edtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(100, 100, 100)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnFiltrar)
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void edtCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtCPFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtCPFActionPerformed

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        try {
            String nome = edtNome.getText().trim();
            String cpf = edtCPF.getText().trim();

            List<String> historico;
            if (nome.isEmpty() && cpf.isEmpty()) {
                historico = funcionarioController.getHistoricoRH();
            } else {
                historico = funcionarioController.filtrarHistorico(nome, cpf);
            }

            atualizarLista(historico);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Erro ao filtrar histórico: " + e.getMessage(), 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnFiltrarActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JTextField edtCPF;
    private javax.swing.JTextField edtNome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JList<String> lstAtividadesRH;
    // End of variables declaration//GEN-END:variables
}
