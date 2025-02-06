/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.gui;

import controller.ClienteController;
import controller.FuncionarioController;
import javax.security.auth.login.LoginException;
import javax.swing.JOptionPane;
import model.Cliente;
import model.Funcionario;

/**
 *
 * @author cayo
 */
public class FrTelaInicial extends javax.swing.JFrame {
    private final ClienteController clienteController;
    private final FuncionarioController funcionarioController; // Declaração do controlador de funcionários

    public FrTelaInicial() {
        initComponents();
        this.clienteController = new ClienteController();
        this.funcionarioController = new FuncionarioController(); // Inicializando o controlador de funcionários
        verificarFuncionarioRH();
        // Teste de conexão com o banco
    funcionarioController.testarConexao();

    }
    
private void verificarFuncionarioRH() {
    try {
        System.out.println("Verificando funcionários no banco...");
        boolean hasFuncionarios = !funcionarioController.getAllFuncionarios().isEmpty();
        System.out.println("Funcionários encontrados: " + hasFuncionarios);

        if (!hasFuncionarios) {
            JOptionPane.showMessageDialog(this, "Nenhum funcionário encontrado. Realize o cadastro do primeiro funcionário.", "Cadastro Inicial", JOptionPane.INFORMATION_MESSAGE);
            abrirTelaCadastroRH();
        }
    } catch (Exception e) {
        System.out.println("Erro ao verificar funcionários: " + e.getMessage());
        e.printStackTrace(); // Mostra o stack trace completo no console
        JOptionPane.showMessageDialog(this, "Erro ao verificar funcionários: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }
}

private void abrirTelaCadastroRH() {
    DlgCadFuncionario telaCadastroRH = new DlgCadFuncionario(new javax.swing.JFrame(), true);
    telaCadastroRH.setVisible(true);
}


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnEntrar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        inputEmail = new javax.swing.JTextField();
        inputSenha = new javax.swing.JPasswordField();
        btnSobre = new javax.swing.JButton();
        btnFormularioCtt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Email");

        jLabel2.setText("Senha:");

        btnEntrar.setText("Entrar");
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Liberation Sans", 1, 36)); // NOI18N
        jLabel5.setText("Sistema Hotel Pet");

        btnSobre.setBackground(new java.awt.Color(255, 153, 153));
        btnSobre.setText("Sobre Nós");
        btnSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSobreActionPerformed(evt);
            }
        });

        btnFormularioCtt.setText("Entre em contato conosco.");
        btnFormularioCtt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFormularioCttActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 150, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(0, 137, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnEntrar)
                        .addGap(291, 291, 291))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnSobre)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(31, 31, 31))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(24, 24, 24)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(inputEmail)
                            .addComponent(inputSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(183, 183, 183)
                        .addComponent(btnFormularioCtt)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSobre)
                .addGap(20, 20, 20)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(inputEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(inputSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(btnEntrar)
                .addGap(40, 40, 40)
                .addComponent(btnFormularioCtt)
                .addGap(24, 24, 24))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
    try {
        String email = inputEmail.getText().trim();
        String senha = new String(inputSenha.getPassword());

        if (email.isEmpty() || senha.isEmpty()) {
            throw new IllegalArgumentException("Email e senha são obrigatórios");
        }

        // Tenta primeiro como funcionário
        Funcionario funcionario = funcionarioController.loginFuncionario(email, senha);
        if (funcionario != null) {
            System.out.println("Cargo do funcionário: " + funcionario.getCargo()); // Debug
            // Limpa os campos antes de abrir a próxima tela
            inputEmail.setText("");
            inputSenha.setText("");
            
            if(funcionario.getCargo().contains("RH") || 
                funcionario.getCargo().contains("gestor") || 
                funcionario.getCargo().equalsIgnoreCase("Gestor de RH")){ //Conta da equipe do suporte da aplicação
            if(funcionario.getEmail().contains("projetohotelpet@vuket.org")){
            abrirMenuSupport(funcionario);
        }else{abrirMenuFuncionarioRH(funcionario);
            }}else {
                JOptionPane.showMessageDialog(this, "Bem-vindo, " + funcionario.getNome() + "!");
                abrirMenuFuncionario(funcionario);
            }
            return;
        }
        

        // Se não encontrou funcionário, tenta como cliente
        Cliente cliente = clienteController.login(email, senha);
        if (cliente != null) {
            // Limpa os campos antes de abrir a próxima tela
            inputEmail.setText("");
            inputSenha.setText("");
            
            JOptionPane.showMessageDialog(this, "Bem-vindo, " + cliente.getNome() + "!");
            abrirMenuCliente(cliente);
            return;
        }

        throw new LoginException("Email ou senha inválidos.");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
    }
    }//GEN-LAST:event_btnEntrarActionPerformed

    private void btnSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSobreActionPerformed
      DlgSobreNós telaSobre = new DlgSobreNós(new javax.swing.JFrame(), true);
      telaSobre.setVisible(true);   
    }//GEN-LAST:event_btnSobreActionPerformed

    private void btnFormularioCttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFormularioCttActionPerformed
    DlgFormularioContato dlg = new DlgFormularioContato(this, true);
    dlg.setVisible(true);
    }//GEN-LAST:event_btnFormularioCttActionPerformed
    private Cliente autenticarCliente(String email, String senha) throws Exception {
    return clienteController.login(email, senha);
}

private Funcionario autenticarFuncionario(String email, String senha) throws Exception {
    return funcionarioController.loginFuncionario(email, senha);
}

private void abrirMenuCliente(Cliente cliente) {
    FrMenu telaMenu = new FrMenu(this, true);
    // Cliente só pode acessar serviços
    telaMenu.setFuncionario(false);
    telaMenu.setVisible(true);
}

private void abrirMenuFuncionario(Funcionario funcionario) {
    FrMenu telaMenu = new FrMenu(this, true);
    // Funcionário comum tem acesso total
    telaMenu.setFuncionario(true);
    telaMenu.setVisible(true);
}

private void abrirMenuFuncionarioRH(Funcionario funcionario) {
    FrfuncionarioRH telaRH = new FrfuncionarioRH(funcionario.getCpf());
    telaRH.setLocationRelativeTo(this); // Centraliza em relação à tela inicial
    telaRH.setFuncionario(true);
    telaRH.setVisible(true);
}
private void abrirMenuSupport(Funcionario funcionario) {
    FrfuncionarioRH telaRH = new FrfuncionarioRH(funcionario.getCpf());
    telaRH.setLocationRelativeTo(this); // Centraliza em relação à tela inicial
    telaRH.setFuncionario(false);
    telaRH.setVisible(true);
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrar;
    private javax.swing.JButton btnFormularioCtt;
    private javax.swing.JButton btnSobre;
    private javax.swing.JTextField inputEmail;
    private javax.swing.JPasswordField inputSenha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}
