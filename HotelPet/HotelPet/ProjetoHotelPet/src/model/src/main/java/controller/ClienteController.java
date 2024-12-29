package controller;

import dao.ClienteDAO;
import dao.ClienteDAOSimulado;
import java.util.List;
import model.Cliente;
import model.exceptions.ReservaException;
import model.valid.ValidateCliente;



public class ClienteController {
    private ClienteDAO clienteDAO;
    private ClienteDAOSimulado clienteDAOSimulado;
    private boolean usarSimulador;

    public ClienteController(boolean usarSimulador) {
        this.usarSimulador = usarSimulador;
        
        if (usarSimulador) {
            this.clienteDAOSimulado = new ClienteDAOSimulado();
        } else {
            this.clienteDAO = new ClienteDAO();
        }
    }

    public void cadastrarCliente(Cliente cliente) throws ReservaException {
        ValidateCliente.validateCPF(cliente.getCpf());
        ValidateCliente.validateEmail(cliente.getEmail());
        if (usarSimulador) {
            clienteDAOSimulado.save(cliente);
        } else {
            clienteDAO.save(cliente);
        }
    }

    public Cliente buscarClientePorCPF(String cpf) throws ReservaException {
        ValidateCliente.validateCPF(cpf);
        if (usarSimulador) {
            return clienteDAOSimulado.findByCPF(cpf);
        } else {
            return clienteDAO.findByCPF(cpf);
        }
    }

    public List<Cliente> listarTodosClientes() {
        if (usarSimulador) {
            return clienteDAOSimulado.findAll();
        } else {
            return clienteDAO.findAll(); 
        }
        
    }

    public void atualizarCliente(Cliente cliente) throws ReservaException {
        
        ValidateCliente.validateCPF(cliente.getCpf());
        ValidateCliente.validateEmail(cliente.getEmail());
        
     if (usarSimulador) {
         Cliente clienteExistente = clienteDAOSimulado.findByCPF(cliente.getCpf());
        if (clienteExistente == null) {
            throw new ReservaException("Cliente não encontrado para atualização.");
        }
        clienteDAOSimulado.update(clienteExistente, cliente);  
        
        } else {
            Cliente clienteExistente = clienteDAO.findByCPF(cliente.getCpf());
        if (clienteExistente == null) {
            throw new ReservaException("Cliente não encontrado para atualização.");
        }
        clienteDAO.update(clienteExistente, cliente);  
        
    }
}

    public void excluirCliente(Cliente cliente) {
        if (usarSimulador) {
            clienteDAOSimulado.delete(cliente);  
        } else {
            clienteDAO.delete(cliente);   
        }
    }
    
    public Cliente findById(int id) {
        return this.clienteDAO.findById(id);
    }
}
