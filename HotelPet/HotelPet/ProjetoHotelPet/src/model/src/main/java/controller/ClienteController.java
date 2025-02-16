package controller;

import java.util.List;

import javax.security.auth.login.LoginException;

import dao.ClienteDAO;
import model.Cliente;
import model.valid.ValidateCliente;

public class ClienteController {

    private ClienteDAO clienteDAO;

    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
    }

    public Cliente login(String email, String senha) throws LoginException {
        ValidateCliente.validateEmail(email);
        Cliente cliente = this.clienteDAO.findByEmail(email);
        
        // Verifica se encontrou o cliente e se não é um funcionário
        if (cliente == null || cliente.getFuncionario()) {
            throw new LoginException("Email ou senha inválidos.");
        }

        // Compara as senhas ignorando espaços em branco
        if (!cliente.getSenha().trim().equals(senha.trim())) {
            throw new LoginException("Email ou senha inválidos.");
        }
        
        return cliente;
    }
    
    public void cadastrarCliente(Cliente cliente) throws Exception {
        ValidateCliente.validateCPF(cliente.getCpf());
        ValidateCliente.validateEmail(cliente.getEmail());
        ValidateCliente.validateCEP(cliente.getCep());
        ValidateCliente.validateTelefone(cliente.getTelefone());
        
        if (clienteDAO.findByEmail(cliente.getEmail()) != null) {
            throw new Exception("O email já está em uso!");
        }

        if (clienteDAO.findByCPF(cliente.getCpf()) != null) {
            throw new Exception("O CPF já está em uso!");
        }

        clienteDAO.save(cliente);
    }

    public void cadastrarFuncionario(Cliente funcionario) throws Exception {
        ValidateCliente.validateCPF(funcionario.getCpf());
        ValidateCliente.validateEmail(funcionario.getEmail());

        if (clienteDAO.findByEmail(funcionario.getEmail()) != null) {
            throw new Exception("O email já está em uso!");
        }

        if (clienteDAO.findByCPF(funcionario.getCpf()) != null) {
            throw new Exception("O CPF já está em uso!");
        }

        funcionario.setFuncionario(true);
        clienteDAO.save(funcionario);
    }

    public Cliente buscarClientePorCPF(String cpf) {
        return clienteDAO.findByCPF(cpf);
    }

    public List<Cliente> listarTodosClientes() {
        return clienteDAO.findAll();
    }

    public List<Cliente> listarTodosClientes(String nome, String cpf) {
        return clienteDAO.findAll(nome, cpf);
    }

    // Método atualizado para permitir alteração de senha: se o campo senha estiver vazio, mantém a senha atual.
    public void atualizarCliente(Cliente novo) throws Exception {
        ValidateCliente.validateCPF(novo.getCpf());
        ValidateCliente.validateEmail(novo.getEmail());
        
        if (clienteDAO.findByEmail(novo.getEmail(), novo.getId()) != null) {
            throw new Exception("O email já está em uso!");
        }

        if (clienteDAO.findByCPF(novo.getCpf(), novo.getId()) != null) {
            throw new Exception("O CPF já está em uso!");
        }
        
        Cliente antigo = clienteDAO.findById(novo.getId());
        
        // Se a senha no objeto novo estiver vazia, mantém a senha atual
        if (novo.getSenha() == null || novo.getSenha().trim().isEmpty()) {
            novo.setSenha(antigo.getSenha());
        }

        
        clienteDAO.update(antigo, novo);
    }

    public void excluirCliente(Cliente cliente) {
        clienteDAO.delete(cliente);
    }

    public Cliente findById(int id) {
        return this.clienteDAO.findById(id);
    }
}