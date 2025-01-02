package controller;

import dao.ClienteDAO;

import java.util.List;
import javax.security.auth.login.LoginException;
import model.Cliente;
import model.valid.ValidateCliente;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class ClienteController {

    private ClienteDAO clienteDAO;

    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
    }

    public Cliente login(String email, String senha) throws LoginException {
        ValidateCliente.validateEmail(email);
        Cliente cliente = this.clienteDAO.findByEmail(email);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        if(cliente == null || !encoder.matches(senha, cliente.getSenha())){
            throw new LoginException();
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

    public void atualizarCliente(Cliente novo) throws Exception {
        ValidateCliente.validateCPF(novo.getCpf());
        ValidateCliente.validateEmail(novo.getEmail());
        
        if (clienteDAO.findByEmail(novo.getEmail(), novo.getId()) != null) {
            throw new Exception("O email já está em uso!");
        }

        if (clienteDAO.findByCPF(novo.getCpf(), novo.getId()) != null) {
            throw new Exception("O CPF já está em uso!");
        }
        
        clienteDAO.update(novo);
    }

    public void excluirCliente(Cliente cliente) {
        clienteDAO.delete(cliente);
    }

    public Cliente findById(int id) {
        return this.clienteDAO.findById(id);
    }
}
