/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.mycompany.gui;

import controller.PetController;
import model.Cliente;
import model.Pet;
import javax.swing.*;
import java.util.List;

public class DlgBuscaPet extends javax.swing.JDialog {

    private final PetController petController;
    private Cliente clienteAtual;

    public DlgBuscaPet(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        petController = new PetController();
    }

    public void setClienteAtual(Cliente cliente) {
        this.clienteAtual = cliente;
        atualizarListaDePets();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBusca = new javax.swing.JButton();
        btnReserva = new javax.swing.JButton();
        btnEditarPet = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        edtNomePet = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstBuscaPet = new javax.swing.JList<>();
        btnExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        btnEditarPet.setText("Editar");
        btnEditarPet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarPetActionPerformed(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Liberation Sans", 1, 36)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Buscar Pet");

        lblNome.setText("Nome:");

        lstBuscaPet.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lstBuscaPet);

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNome)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnBusca)
                                .addGap(18, 18, 18)
                                .addComponent(btnReserva)))
                        .addGap(18, 18, 18)
                        .addComponent(btnEditarPet)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluir))
                    .addComponent(edtNomePet))
                .addContainerGap(24, Short.MAX_VALUE))
            .addComponent(lblTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edtNomePet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBusca)
                    .addComponent(btnReserva)
                    .addComponent(btnEditarPet)
                    .addComponent(btnExcluir))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void atualizarListaDePets() {
        List<Pet> pets = petController.listarPetsPorCliente(clienteAtual.getId());
        DefaultListModel<String> listModel = new DefaultListModel<>();

        for (Pet pet : pets) {
            listModel.addElement("ID: " + pet.getId() + " - Nome: " + pet.getNome());
        }

        lstBuscaPet.setModel(listModel);
    }

    private void btnBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaActionPerformed

    }//GEN-LAST:event_btnBuscaActionPerformed

    private void btnEditarPetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarPetActionPerformed
        String petSelecionado = lstBuscaPet.getSelectedValue();

        if (petSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um pet.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!petSelecionado.contains("ID: ")) {
            JOptionPane.showMessageDialog(this, "Formato inválido da entrada selecionada.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String id = petSelecionado.split("ID: ")[1].toString().split(" - Nome: ")[0];
        Pet pet = petController.findById(Integer.parseInt(id));

        if (pet == null) {
            JOptionPane.showMessageDialog(this, "Pet não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DlgCadPet dlgCadPet = new DlgCadPet(new javax.swing.JFrame(), true);
        dlgCadPet.setCPFResponsavel(pet.getCliente().getCpf());
        dlgCadPet.setResponsavelId(pet.getCliente().getId());
        dlgCadPet.setPet(pet);
        dlgCadPet.setVisible(true);

        atualizarListaDePets();
    }//GEN-LAST:event_btnEditarPetActionPerformed

    private void btnReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservaActionPerformed

        String petSelecionado = lstBuscaPet.getSelectedValue(); // Retorna o item selecionado

        if (petSelecionado == null || petSelecionado.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione um pet para realizar a reserva.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Busca o pet pelo id
            String id = petSelecionado.split("ID: ")[1].toString().split(" - Nome: ")[0];
            Pet pet = petController.findById(Integer.parseInt(id));

            if (pet == null) {
                JOptionPane.showMessageDialog(this, "Pet não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtém o cliente associado ao pet
            Cliente clienteAssociado = pet.getCliente();

            if (clienteAssociado == null) {
                JOptionPane.showMessageDialog(this, "O pet não está associado a um cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Abre a tela de reservas
            DlgReservas dlgReservas = new DlgReservas(new javax.swing.JFrame(), true, clienteAssociado.getId(), pet.getId());
            dlgReservas.setVisible(true);

        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(this, "Formato inválido do item selecionado.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao processar a seleção do pet.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnReservaActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        String petSelecionado = lstBuscaPet.getSelectedValue();

        if (petSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um pet.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Extrai o ID do pet a partir do texto exibido
            String id = petSelecionado.split("ID: ")[1].split(" - Nome: ")[0].trim();
            
            // Confirmação para exclusão
            int resposta = JOptionPane.showConfirmDialog(this,
                    "Tem certeza de que deseja excluir este pet?",
                    "Confirmar Exclusão",
                    JOptionPane.YES_NO_OPTION);

            if (resposta != JOptionPane.YES_OPTION) {
                return; // Usuário cancelou a exclusão
            }

            // Realiza a exclusão do pet
            petController.excluirPet(Integer.parseInt(id));

            // Verifica se o pet foi marcado como inativo
            Pet pet = petController.findById(Integer.parseInt(id));
            if (pet != null && !pet.getStatus()) {
                JOptionPane.showMessageDialog(this, "Pet excluído com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                atualizarListaDePets(); // Atualiza a lista após a exclusão
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao excluir o pet.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(this, "Formato inválido do item selecionado.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID do pet inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir o pet.", "Erro", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_btnExcluirActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBusca;
    private javax.swing.JButton btnEditarPet;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnReserva;
    private javax.swing.JTextField edtNomePet;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JList<String> lstBuscaPet;
    // End of variables declaration//GEN-END:variables
}
