package dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import model.Pet;
import com.google.gson.*;

public class PetDAOSimulado implements IDao<Pet> {
    private static final String FILE_PATH = "pets.json";
    private final Gson gson = new Gson();

    @Override
    public List<Pet> findAll() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            Pet[] pets = gson.fromJson(reader, Pet[].class);
            return pets != null ? List.of(pets) : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Pet find(Pet obj) {
        return findAll().stream()
                .filter(pet -> pet.getId() == obj.getId())
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(Pet obj) {
        List<Pet> pets = findAll();
        pets.add(obj);
        writeToFile(pets);
    }

    @Override
    public void update(Pet obj, Pet novo) {
        List<Pet> pets = findAll();
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i).getId() == obj.getId()) {
                pets.set(i, obj);
                break;
            }
        }
        writeToFile(pets);
    }

    @Override
    public boolean delete(Pet obj) {
        List<Pet> pets = findAll();
        boolean removed = pets.removeIf(pet -> pet.getId() == obj.getId());
        writeToFile(pets);
        return removed;
    }

    private void writeToFile(List<Pet> pets) {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(pets, writer);
        } catch (IOException e) {
        }
    }
}
