package controller;

import dao.ClienteDAO;
import dao.PetDAO;
<<<<<<< HEAD
import dao.PetDAOSimulado;
import java.util.List;
=======
import model.Cliente;
>>>>>>> Main
import model.Pet;
import model.valid.ValidatePet;

import java.util.List;
import model.exceptions.PetException;

public class PetController {

    private PetDAO petDAO;
    private ClienteDAO clienteDAO;

    public PetController() {
        this.petDAO = new PetDAO();
        this.clienteDAO = new ClienteDAO();
    }

    public void cadastrarPet(Pet pet, Integer responsavelId) throws PetException {
        ValidatePet.validateNome(pet.getNome());
        ValidatePet.validateData(pet.getDatanascimento());
        Cliente cliente = clienteDAO.findById(responsavelId);
        if (cliente == null) {
            throw new PetException("Cliente não encontrado");
        }

        pet.setCliente(cliente);
        petDAO.save(pet);
    }

<<<<<<< HEAD
    public List<Pet> listarTodosPets() {
        if (usarSimulador) {
            return petDAOSimulado.findAll();
        } else {
            return petDAO.findAll(); 
        }
=======
    public List<Pet> listarPetsPorCliente(int clienteId) {
        return petDAO.findByClienteId(clienteId);
>>>>>>> Main
    }
    

<<<<<<< HEAD
    public void atualizarPet(Pet pet, Pet novo) throws ReservaException {
        if (usarSimulador) {
            petDAOSimulado.update(pet, novo);
        } else {
            petDAO.update(novo, novo);
=======
    public void atualizarPet(Pet novo, Integer responsavelId) {
        ValidatePet.validateNome(novo.getNome());
        ValidatePet.validateData(novo.getDatanascimento());
        Cliente cliente = clienteDAO.findById(responsavelId);
        if (cliente == null) {
            throw new PetException("Cliente não encontrado");
>>>>>>> Main
        }

        novo.setCliente(cliente);
        petDAO.update(novo);
    }

    public void excluirPet(int petId) {
        Pet pet = findById(petId);  // Busca o pet pelo ID

        if (pet != null) {
            // Cria um novo objeto Pet com os mesmos dados, mas com o campo 'Status' como false
            Pet novoPet = new Pet();
            novoPet.setId(pet.getId());
            novoPet.setNome(pet.getNome());
            novoPet.setDatanascimento(pet.getDatanascimento());
            novoPet.setEspecie(pet.getEspecie());
            novoPet.setRaca(pet.getRaca());
            novoPet.setPorte(pet.getPorte());
            novoPet.setSexo(pet.getSexo());
            novoPet.setCaracteristicasFisicas(pet.getCaracteristicasFisicas());
            novoPet.setHistoricoDoencas(pet.getHistoricoDoencas());
            novoPet.setMedicacoes(pet.getMedicacoes());
            novoPet.setStatus(false);

            // Atualiza o pet no banco de dados, passando o pet original e o novo com 'Status' como false
            petDAO.update(pet, novoPet);
        }
        // Caso o pet não seja encontrado, você pode simplesmente não fazer nada ou lançar uma exceção
    }

    public Pet findById(int id) {
        return this.petDAO.findById(id); // Encontra pet pelo id
    }

    // Método no PetController para buscar pet por nome
    public Pet buscarPetPorNome(String nomePet) {
        List<Pet> pets = petDAO.findAll();  // Obtém todos os pets
        for (Pet pet : pets) {
            if (pet.getNome().equalsIgnoreCase(nomePet)) {  // Verifica se o nome corresponde
                return pet;
            }
        }
        return null;  // Retorna null se não encontrar o pet
    }
}
