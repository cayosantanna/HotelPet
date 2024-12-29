/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.mycompany.gui;

import controller.PetController;
import controller.ClienteController;
import java.text.ParseException;
import model.Cliente;
import model.Pet;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Reserva;

public class DlgBuscaPet extends javax.swing.JDialog {
    private List<Reserva> listaReservas = new ArrayList<>();

    private PetController petController;
    private ClienteController clienteController;
    private DefaultListModel<String> listModelPets;

    /**
     * Creates new form DlgBuscaPet
     */
    public DlgBuscaPet(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        petController = new PetController(); 
        clienteController = new ClienteController(); 
        listModelPets = new DefaultListModel<>();
        lstBuscaPet.setModel(listModelPets);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBusca = new javax.swing.JButton();
        btnReserva = new javax.swing.JButton();
        btnEditarCliente = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        edtNomePet = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstBuscaPet = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();

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

        btnEditarCliente.setText("Editar");
        btnEditarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarClienteActionPerformed(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Liberation Sans", 1, 36)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Buscar Pet");

        lblNome.setText("Nome:");

        edtNomePet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtNomePetActionPerformed(evt);
            }
        });

        lstBuscaPet.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lstBuscaPet);

        jButton1.setText("Selecionar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNome)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnBusca)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(btnReserva)))
                        .addGap(18, 18, 18)
                        .addComponent(btnEditarCliente))
                    .addComponent(edtNomePet, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEditarCliente)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnBusca)
                        .addComponent(jButton1)
                        .addComponent(btnReserva)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaActionPerformed
        String nomeBusca = edtNomePet.getText().trim().toLowerCase();
    List<Pet> pets = petController.listarTodosPets();  // Carregar todos os pets
    listModelPets.clear();  // Limpar a lista antes de adicionar novos itens

    // Buscar pets que contenham o nome informado
    for (Pet pet : pets) {
        if (pet.getNome().toLowerCase().contains(nomeBusca)) {
            listModelPets.addElement("ID: " + pet.getId() + " - Nome: " + pet.getNome());
        }
    }
    }//GEN-LAST:event_btnBuscaActionPerformed

    private void btnEditarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarClienteActionPerformed
         String selectedValue = lstBuscaPet.getSelectedValue();

        if (selectedValue != null) {
            int petId = Integer.parseInt(selectedValue.split(":")[1].trim().split(" ")[0]);
            Pet petSelecionado = petController.listarTodosPets().stream()
                    .filter(pet -> pet.getId() == petId).findFirst().orElse(null);

            if (petSelecionado != null) {
                Cliente clienteResponsavel = clienteController.buscarClientePorId(petSelecionado.getCliente().getId());

                if (clienteResponsavel != null) {
                    JOptionPane.showMessageDialog(this,
                            "Reserva Criada!\nCliente: " + clienteResponsavel.getNome() + "\nCPF: "
                                    + clienteResponsavel.getCpf() + "\nPet: " + petSelecionado.getNome(),
                            "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Cliente não encontrado para este pet!", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Pet não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um pet antes de reservar!", "Aviso",
                    JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarClienteActionPerformed

    private void edtNomePetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtNomePetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtNomePetActionPerformed

    private void btnReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservaActionPerformed
                                         
    String petSelecionado = lstBuscaPet.getSelectedValue(); 
    
    if (petSelecionado != null) {  
        
        Pet pet = encontrarPetPorNome(petSelecionado);  
        if (pet != null) {
    Reserva novaReserva = null;
            try {
                novaReserva = new Reserva(pet); // Criação de uma nova reserva com o pet
            } catch (ParseException ex) {
                Logger.getLogger(DlgBuscaPet.class.getName()).log(Level.SEVERE, null, ex);
            }
    listaReservas.add(novaReserva);  
    JOptionPane.showMessageDialog(this, "Pet reservado com sucesso!", "Reserva", JOptionPane.INFORMATION_MESSAGE);
}

    } else {
        JOptionPane.showMessageDialog(this, "Por favor, selecione um pet.", "Aviso", JOptionPane.WARNING_MESSAGE);
    }
    }//GEN-LAST:event_btnReservaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     String petSelecionado = lstBuscaPet.getSelectedValue();
        if (petSelecionado != null) {
            JOptionPane.showMessageDialog(this, "Pet selecionado: " + petSelecionado, "Detalhes do Pet", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um pet.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    
    private Pet encontrarPetPorNome(String nomePet) {
    List<Pet> pets = petController.listarTodosPets();  // Carregar todos os pets

    for (Pet p : pets) {
        if (p.getNome().equalsIgnoreCase(nomePet)) {
            return p;  
        }
    }
    return null;
}

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBusca;
    private javax.swing.JButton btnEditarCliente;
    private javax.swing.JButton btnReserva;
    private javax.swing.JTextField edtNomePet;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JList<String> lstBuscaPet;
    // End of variables declaration//GEN-END:variables
}
