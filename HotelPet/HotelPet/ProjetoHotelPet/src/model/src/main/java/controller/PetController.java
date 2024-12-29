/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.PetDAO;
import java.util.List;
import model.Pet;
import model.exceptions.ReservaException;
import model.valid.ValidatePet;

public class PetController {
    private PetDAO petDAO;

    public PetController(boolean usarSimulador) {
        
            this.petDAO = new PetDAO();
    }

    public void cadastrarPet(Pet pet) throws ReservaException {
        ValidatePet.validateNome(pet.getNome());
        ValidatePet.validateCpfResponsavel(pet.getCpfResponsavel());

            petDAO.save(pet);
    }

    public List<Pet> listarTodosPets() {
        return petDAO.findAll();
    }
    
    public void atualizarPet(Pet pet, Pet novo) throws ReservaException {
        petDAO.update(pet, novo);  // Alterar para usar dois parâmetros também
}



    public void excluirPet(Pet pet) {
            petDAO.delete(pet);
    }
    
     public Pet findById(int id) {
        return this.petDAO.findById(id);
    }
}


