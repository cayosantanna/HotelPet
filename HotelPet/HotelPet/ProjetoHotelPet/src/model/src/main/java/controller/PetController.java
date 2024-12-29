/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ClienteDAO;
import dao.PetDAO;
import java.util.List;
import model.Cliente;
import model.Pet;
import model.valid.ValidatePet;

public class PetController {

    private PetDAO petDAO;
    private ClienteDAO clienteDAO;
    
    public PetController() {
        this.petDAO = new PetDAO();
        this.clienteDAO = new ClienteDAO();
    }

    public void cadastrarPet(Pet pet, Integer responsavelId) {
        ValidatePet.validateNome(pet.getNome());
        Cliente cliente = clienteDAO.findById(responsavelId);
        pet.setCliente(cliente);
        petDAO.save(pet);
    }

    public List<Pet> listarTodosPets() {
        return petDAO.findAll();
    }

    public void atualizarPet(Pet pet, Pet novo) {
        petDAO.update(pet, novo);  // Alterar para usar dois parâmetros também
    }

    public void excluirPet(Pet pet) {
        petDAO.delete(pet);
    }

    public Pet findById(int id) {
        return this.petDAO.findById(id);
    }
}
