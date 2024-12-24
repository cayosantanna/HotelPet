/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.PetDAO;
import dao.PetDAOSimulado;
import java.util.List;
import model.Pet;
import model.exceptions.ReservaException;
import model.valid.ValidatePet;

public class PetController {
    private PetDAO petDAO;
    private PetDAOSimulado petDAOSimulado;
    private boolean usarSimulador;

    public PetController(boolean usarSimulador) {
        this.usarSimulador = usarSimulador;
        if (usarSimulador) {
            this.petDAOSimulado = new PetDAOSimulado();
        } else {
            this.petDAO = new PetDAO();
        }
    }

    public void cadastrarPet(Pet pet) throws ReservaException {
        ValidatePet.validateNome(pet.getNome());
        ValidatePet.validateCpfResponsavel(pet.getCpfResponsavel());
        if (usarSimulador) {
            petDAOSimulado.save(pet);
        } else {
            petDAO.save(pet);
        }
    }

    public List<Pet> listarTodosPets() {
        if (usarSimulador) {
            return petDAOSimulado.findAll();
        } else {
            return petDAO.findAll(); 
        }
    }
    

    public void atualizarPet(Pet pet, Pet novo) throws ReservaException {
        if (usarSimulador) {
            petDAOSimulado.update(pet, novo);
        } else {
            petDAO.update(novo, novo);
        }
    }

    public void excluirPet(Pet pet) {
        if (usarSimulador) {
            petDAOSimulado.delete(pet);
        } else {
            petDAO.delete(pet);
        }
    }
    
     public Pet findById(int id) {
        return this.petDAO.findById(id);
    }
}


