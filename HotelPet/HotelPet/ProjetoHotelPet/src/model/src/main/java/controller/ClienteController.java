
package controller;

import dao.ClienteDAO;

import java.util.List;
import model.Cliente;
import model.exceptions.ReservaException;
import model.valid.ValidateCliente;



public class ClienteController {
    private ClienteDAO clienteDAO;


    public ClienteController(boolean par) {
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

    public void atualizarCliente(Cliente cliente) throws ReservaException {
        
        ValidateCliente.validateCPF(cliente.getCpf());
        ValidateCliente.validateEmail(cliente.getEmail());
        
            Cliente clienteExistente = clienteDAO.findByCPF(cliente.getCpf());
        if (clienteExistente == null) {
            throw new ReservaException("Cliente não encontrado para atualização.");
        }
        clienteDAO.update(clienteExistente, cliente);  
        
    }


    public void excluirCliente(Cliente cliente) {
            clienteDAO.delete(cliente);   
    }
    
    public Cliente findById(int id) {
        return this.clienteDAO.findById(id);
    }
}
