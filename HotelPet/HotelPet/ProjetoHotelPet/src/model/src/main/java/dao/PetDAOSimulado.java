/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import model.Pet;

public class PetDAOSimulado implements IDao<Pet> {
    private static final String FILE_PATH = "pets.txt";

    @Override
    public List<Pet> findAll() {
        List<Pet> pets = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                Pet pet = new Pet(
                        Integer.parseInt(data[0]), 
                        data[1], 
                        data[2], 
                        data[3],
                        data[4], 
                        data[5], 
                        data[6],
                        data[7], 
                        data[8], 
                        data[9],  
                        data[10]
                );
                pets.add(pet);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pets;
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(obj.getId() + ";" + obj.getCpfResponsavel() + ";" + obj.getDatanascimento() + ";" + obj.getNome() + ";" + 
                         obj.getEspecie() + ";" + obj.getRaca() + ";" + obj.getPorte() + ";" + 
                         obj.getSexo() + ";" + obj.getCaracteristicasFisicas() + ";" + 
                         obj.getHistoricoDoencas() + ";" + obj.getMedicacoes());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(Pet obj, Pet novo) {
        List<Pet> pets = findAll();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Pet pet : pets) {
                if (pet.getId() == obj.getId()) {
                    pet = novo; 
                }
                writer.write(pet.getId() + ";" + pet.getCpfResponsavel() + ";" + pet.getNome() + ";" +
                             pet.getEspecie() + ";" + pet.getRaca() + ";" + pet.getPorte() + ";" + 
                             pet.getSexo() + ";" + pet.getCaracteristicasFisicas() + ";" +
                             pet.getHistoricoDoencas() + ";" + pet.getMedicacoes());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(Pet obj) {
        List<Pet> pets = findAll();
        boolean removed = pets.removeIf(pet -> pet.getId() == obj.getId());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Pet pet : pets) {
                writer.write(pet.getId() + ";" + pet.getCpfResponsavel() + ";" + pet.getNome() + ";" +
                             pet.getEspecie() + ";" + pet.getRaca() + ";" + pet.getPorte() + ";" + 
                             pet.getSexo() + ";" + pet.getCaracteristicasFisicas() + ";" +
                             pet.getHistoricoDoencas() + ";" + pet.getMedicacoes());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return removed;
    }

    @Override
    public void update(Pet obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
