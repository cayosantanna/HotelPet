/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.mycompany.gui;

import controller.PetController;
import controller.ClienteController;
import model.Cliente;
import model.Pet;
import javax.swing.*;
import java.util.List;
import static org.hibernate.criterion.Projections.id;

public class DlgBuscaPet extends javax.swing.JDialog {

    private PetController petController;
    private ClienteController clienteController;
    private DefaultListModel<String> listModelPets;
    private Cliente clienteAtual; // A variável que armazena o cliente atual

    public DlgBuscaPet(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        petController = new PetController(); 
        clienteController = new ClienteController(); 
        listModelPets = new DefaultListModel<>();
        lstBuscaPet.setModel(listModelPets);
    }
    
    public void setClienteAtual(Cliente cliente) {
    this.clienteAtual = cliente;
    atualizarListaDePets(); // Atualiza a lista sempre que o cliente for definido
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
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaActionPerformed
                                       
    String nomeBusca = edtNomePet.getText().trim().toLowerCase();
    listModelPets.clear();

    if (nomeBusca.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, insira um nome para buscar.", "Aviso", JOptionPane.WARNING_MESSAGE);
        return;
    }

    if (clienteAtual == null) {
        JOptionPane.showMessageDialog(this, "Nenhum cliente selecionado.", "Aviso", JOptionPane.WARNING_MESSAGE);
        return;
    }

    List<Pet> pets = petController.listarPetsPorCliente(clienteAtual.getId());

    boolean encontrouPet = false;
    for (Pet pet : pets) {
        if (pet.getStatus() && pet.getNome().toLowerCase().contains(nomeBusca)) {
            listModelPets.addElement("ID: " + pet.getId() + " - Nome: " + pet.getNome());
            encontrouPet = true;
        }
    }

    if (!encontrouPet) {
        JOptionPane.showMessageDialog(this, "Nenhum pet encontrado com esse nome.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
    }

    }//GEN-LAST:event_btnBuscaActionPerformed

    private void btnEditarPetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarPetActionPerformed
                                             
    // Obtém o pet selecionado na lista
    String petSelecionado = lstBuscaPet.getSelectedValue();

    if (petSelecionado == null) {
        JOptionPane.showMessageDialog(this, "Por favor, selecione um pet.", "Aviso", JOptionPane.WARNING_MESSAGE);
        return;
    }

    if (!petSelecionado.contains("ID: ")) {
        JOptionPane.showMessageDialog(this, "Formato inválido da entrada selecionada.", "Erro", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Extrai o nome do pet da string selecionada
    String nomePet = petSelecionado.split("ID: ")[1].toString().split(" - Nome: ")[1];

    // Busca o pet pelo nome
    Pet pet = petController.buscarPetPorNome(nomePet);

    if (pet == null) {
        JOptionPane.showMessageDialog(this, "Pet não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Obtém o CPF e ID do responsável (presumindo que você tenha esses dados disponíveis)
    String cpfResponsavel = pet.getCliente().getCpf();  // Exemplo de como acessar o CPF do responsável
    Integer responsavelId = pet.getCliente().getId();   // Exemplo de como acessar o ID do responsável

    // Cria a janela de cadastro de pet com os dados do pet
    DlgCadPet dlgCadPet;
    dlgCadPet = new DlgCadPet(new javax.swing.JFrame(), true, cpfResponsavel, responsavelId, pet);
    dlgCadPet.setDlgBuscaPet(this); // Passa a referência do DlgBuscaPet
    dlgCadPet.setVisible(true);

    // Após a edição, atualiza a lista de pets
    atualizarListaDePets();


    }//GEN-LAST:event_btnEditarPetActionPerformed

    private void edtNomePetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtNomePetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtNomePetActionPerformed

    private void btnReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservaActionPerformed

    String nomePetSelecionado = lstBuscaPet.getSelectedValue(); // Retorna o item selecionado
    if (nomePetSelecionado != null && !nomePetSelecionado.trim().isEmpty()) {
        // Busca o pet pelo nome
        Pet petSelecionado = petController.buscarPetPorNome(nomePetSelecionado.split(" - Nome: ")[1].trim());
        if (petSelecionado != null) {
            // Obtém o cliente associado ao pet
            Cliente clienteAssociado = petSelecionado.getCliente(); // Pet já tem um cliente associado

            if (clienteAssociado != null) {
                // Passa o 'this' como referência para a janela pai (DlgBuscaPet)
                DlgReservas dlgReservas = new DlgReservas(new javax.swing.JFrame(), true, clienteAssociado.getId(), petSelecionado.getId());
                dlgReservas.setVisible(true); // Exibe a tela de reserva
            } else {
                JOptionPane.showMessageDialog(this, "O pet não está associado a um cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pet não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Selecione um pet para realizar a reserva.", "Erro", JOptionPane.ERROR_MESSAGE);
    }

    }//GEN-LAST:event_btnReservaActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
    String petSelecionado = lstBuscaPet.getSelectedValue();
    if (petSelecionado == null) {
        JOptionPane.showMessageDialog(this, "Por favor, selecione um pet.", "Aviso", JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    // Extrai o ID do pet a partir do texto exibido
    String idString = petSelecionado.split("ID: ")[1].split(" - Nome: ")[0];
    int petId = Integer.parseInt(idString);

    // Solicita a exclusão do pet
    int resposta = JOptionPane.showConfirmDialog(this, "Tem certeza de que deseja excluir este pet?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
    if (resposta == JOptionPane.YES_OPTION) {
        petController.excluirPet(petId);  // Apenas chama o método sem capturar um valor de retorno

        // Verificar se o pet foi marcado como inativo e se a operação foi bem-sucedida
        Pet pet = petController.findById(petId); // Verifica o estado atual do pet
        if (pet != null && !pet.getStatus()) {
            JOptionPane.showMessageDialog(this, "Pet excluído com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            atualizarListaDePets();  // Atualiza a lista de pets após a exclusão
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao excluir o pet.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    }//GEN-LAST:event_btnExcluirActionPerformed
void atualizarListaDePets() {
    listModelPets.clear();
    if (clienteAtual != null) {
        List<Pet> pets = petController.listarPetsPorCliente(clienteAtual.getId());
        for (Pet pet : pets) {
            if (pet.getStatus()) {  // Apenas pets ativos
                listModelPets.addElement("ID: " + pet.getId() + " - Nome: " + pet.getNome());
            }
        }
    }
}


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
