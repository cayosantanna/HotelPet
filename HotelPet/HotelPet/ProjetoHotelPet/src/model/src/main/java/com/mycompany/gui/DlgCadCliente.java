package com.mycompany.gui;

import controller.ClienteController;
import java.util.List;
import model.Cliente;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class DlgCadCliente extends javax.swing.JDialog {
    private ClienteController clienteController = new ClienteController();
    private Cliente cliente;
    
    private DefaultListModel<String> listModel; // Modelo da lista
    private JList<String> jListBuscaCliente;    // JList para exibir os clientes
    
    public DlgCadCliente(java.awt.Frame parent, boolean modal, Cliente cliente){
         super(parent, modal);
         initComponents();
        
        
        // Inicializa o modelo da JList
        listModel = new DefaultListModel<>();
        
        // Inicializa a JList com o modelo
        jListBuscaCliente = new JList<>(listModel);
        
        // Adiciona a JList a um JScrollPane
        JScrollPane jScrollPane = new JScrollPane(jListBuscaCliente);
        jScrollPane.setBounds(20, 120, 250, 150);  // Ajuste as coordenadas conforme necessário
        
        // Adiciona o JScrollPane ao painel ou layout principal (supondo que você tenha um painel de conteúdo)
        getContentPane().setLayout(null);  // Layout nulo para controle manual de componentes
        getContentPane().add(jScrollPane);  // Adiciona o JScrollPane com a JList à janela
        
        // Atualiza a lista de clientes
        atualizarListaClientes();
        
        // Preenche os dados do cliente, caso ele tenha sido passado
        if (cliente != null) {
            preencherDados(cliente);
        }
    }
    
    // Método para atualizar a JList com a lista de clientes
    public void atualizarListaClientes() {
        listModel.clear(); // Limpa a lista antes de adicionar novos clientes
        
        // Obtém a lista de clientes do controller
        List<Cliente> clientes = clienteController.listarClientes();
        
        // Adiciona cada cliente no modelo da JList
        for (Cliente cliente : clientes) {
            listModel.addElement(cliente.toString()); // Usando o método toString() da classe Cliente
        }
    }
    
    // Preenche os campos com os dados do cliente
    public void preencherDados(Cliente cliente) {
        edtNome.setText(cliente.getNome());
        edtCPF.setText(cliente.getCpf());
        edtEmail.setText(cliente.getEmail());
        edtTelefone.setText(cliente.getTelefone());
        edtCEP.setText(cliente.getCep());
        edtEndereço.setText(cliente.getEndereco());
    }

    private void btnConfirmaActionPerformed(java.awt.event.ActionEvent evt) {                                            
        Cliente cliente = new Cliente(
            edtNome.getText(),
            edtCPF.getText(),
            edtEmail.getText(),
            edtTelefone.getText(),
            edtCEP.getText(),
            edtEndereço.getText()
        );

        // Salva o cliente no controller
        clienteController.salvarCliente(cliente);

        // Atualiza a JList com o cliente recém-adicionado
        atualizarListaClientes();

        // Exibe a mensagem de sucesso
        JOptionPane.showMessageDialog(this, "Cliente salvo com sucesso!");
        dispose(); // Fecha a janela
    }                                           
    
    private void btnCancelaActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // Fecha a janela sem salvar
        dispose();
    }                                          

    private void edtCEPActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
    }                                      

    private void edtEndereçoActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DlgCadCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Cliente cliente = new Cliente(); // Inicialize conforme necessário.

                // Criação do diálogo.
                DlgCadCliente dialog = new DlgCadCliente(new javax.swing.JFrame(), true, cliente);

                // Adicionando um WindowListener para lidar com o evento de fechamento.
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });

                // Exibindo o diálogo.
                dialog.setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify                     
    private javax.swing.JButton btnCancela;
    private javax.swing.JButton btnConfirma;
    private javax.swing.JTextField edtCEP;
    private javax.swing.JTextField edtCPF;
    private javax.swing.JTextField edtEmail;
    private javax.swing.JTextField edtEndereço;
    private javax.swing.JTextField edtNome;
    private javax.swing.JTextField edtTelefone;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration                   
}

