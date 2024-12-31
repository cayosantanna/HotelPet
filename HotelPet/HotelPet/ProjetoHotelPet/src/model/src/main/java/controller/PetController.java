package controller;

import dao.ClienteDAO;
import dao.PetDAO;
import model.Cliente;
import model.Pet;
import model.valid.ValidatePet;

import java.util.List;
import java.util.stream.Collectors;

public class PetController {

    private PetDAO petDAO;
    private ClienteDAO clienteDAO;

    public PetController() {
        this.petDAO = new PetDAO();
        this.clienteDAO = new ClienteDAO();
    }

    public void cadastrarPet(Pet pet, Integer responsavelId) {
        ValidatePet.validateNome(pet.getNome()); // Validação do nome do pet
        Cliente cliente = clienteDAO.findById(responsavelId); // Busca o responsável pelo id
        if (cliente != null) {
            pet.setCliente(cliente); // Associa o responsável ao pet
            petDAO.save(pet); // Persiste o pet no banco
        } else {
            System.out.println("Cliente não encontrado!");
        }
    }
    
    public List<Pet> listarPetsPorCliente(int clienteId) {
    return petDAO.findByClienteId(clienteId);
}


public List<Pet> listarTodosPets() {
    return petDAO.findAll().stream()
                 .filter(pet -> pet != null && pet.getStatus() != null && pet.getStatus())  // Verifica se o status é não nulo e se é verdadeiro
                 .collect(Collectors.toList());
}

    public void atualizarPet(Pet pet, Pet novo) {
        petDAO.update(pet, novo); // Atualiza o pet
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

