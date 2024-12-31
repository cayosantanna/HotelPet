
package controller;

import dao.ClienteDAO;

import java.util.List;
import model.Cliente;
import model.exceptions.ReservaException;
import model.valid.ValidateCliente;



public class ClienteController {
    private ClienteDAO clienteDAO;
    
    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
    }

    public void cadastrarCliente(Cliente cliente) throws ReservaException {
        ValidateCliente.validateCPF(cliente.getCpf());
        ValidateCliente.validateEmail(cliente.getEmail());

        clienteDAO.save(cliente);
    }

    public Cliente buscarClientePorCPF(String cpf) throws ReservaException {
        ValidateCliente.validateCPF(cpf);

        return clienteDAO.findByCPF(cpf);
    }

    public List<Cliente> listarTodosClientes() {
        return clienteDAO.findAll();
    }
    
    public List<Cliente> listarTodosClientes(String nome, String cpf) {
        return clienteDAO.findAll(nome, cpf);
    }

    public void atualizarCliente(Cliente cliente, Cliente novo) throws ReservaException {
    ValidateCliente.validateCPF(cliente.getCpf());
    ValidateCliente.validateEmail(cliente.getEmail());
    clienteDAO.update(cliente, novo);  
}


    public void excluirCliente(Cliente cliente) {
            clienteDAO.delete(cliente);   
    }
    
    public Cliente findById(int id) {
        return this.clienteDAO.findById(id);
    }
}