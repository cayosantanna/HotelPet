/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Pet;
import factory.Persistencia;
import java.util.stream.Collectors;

public class PetDAO implements IDao<Pet> {

    @Override
    public List<Pet> findAll() {
        List<Pet> pets = new ArrayList<>();
        String sql = "SELECT * FROM pet"; 
        
        try (Connection connection = Persistencia.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
             
            while (resultSet.next()) {
                Pet pet = new Pet(
                        resultSet.getInt("id"),
                        resultSet.getString("cpfResponsavel"),
                        resultSet.getString("datanascimento"),
                        resultSet.getString("nome"),
                        resultSet.getString("especie"),
                        resultSet.getString("raca"),
                        resultSet.getString("porte"),
                        resultSet.getString("sexo"),
                        resultSet.getString("caracteristicasFisicas"),
                        resultSet.getString("historicoDoencas"),
                        resultSet.getString("medicacoes")
                        
                );
                pets.add(pet);  
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        
        return pets;  
    }

  
    public List<Pet> findByResponsavel(String cpfResponsavel) {
        return findAll().stream()
                .filter(pet -> pet.getCpfResponsavel().equals(cpfResponsavel))
                .collect(Collectors.toList());
    }

    @Override
    public void save(Pet pet) {
        String sql = "INSERT INTO pet (nome, cpfResponsavel, especie, raca, porte) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection connection = Persistencia.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
             
            statement.setString(1, pet.getNome());
            statement.setString(2, pet.getCpfResponsavel());
            statement.setString(3, pet.getEspecie());
            statement.setString(4, pet.getRaca());
            statement.setString(5, pet.getPorte());
            statement.setInt(6, pet.getId());
            statement.setString(7, pet.getSexo());
            statement.setString(8, pet.getCaracteristicasFisicas());
            statement.setString(9, pet.getHistoricoDoencas());
            statement.setString(10, pet.getMedicacoes());
            
            statement.executeUpdate();  
        } catch (SQLException e) {
            e.printStackTrace();  
        }
    }

    @Override
    public void update(Pet pet) {
        String sql = "UPDATE pet SET nome = ?, cpfResponsavel = ?, especie = ?, raca = ?, porte = ? WHERE id = ?";
        
        try (Connection connection = Persistencia.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
             
            statement.setString(1, pet.getNome());
            statement.setString(2, pet.getCpfResponsavel());
            statement.setString(3, pet.getEspecie());
            statement.setString(4, pet.getRaca());
            statement.setString(5, pet.getPorte());
            statement.setInt(6, pet.getId());
            statement.setString(7, pet.getSexo());
            statement.setString(8, pet.getCaracteristicasFisicas());
            statement.setString(9, pet.getHistoricoDoencas());
            statement.setString(10, pet.getMedicacoes());
            
            statement.executeUpdate(); 
        } catch (SQLException e) {
            e.printStackTrace();  
        }
    }

    @Override
    public boolean delete(Pet pet) {
        String sql = "DELETE FROM pet WHERE id = ?";
        
        try (Connection connection = Persistencia.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
             
            statement.setInt(1, pet.getId());
            int rowsAffected = statement.executeUpdate();  
            return rowsAffected > 0;  
        } catch (SQLException e) {
            e.printStackTrace();  
        }
        return false;
    }

    @Override
    public Pet find(Pet pet) {
        String sql = "SELECT * FROM pet WHERE id = ?";
        
        try (Connection connection = Persistencia.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
             
            statement.setInt(1, pet.getId());
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                return new Pet(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("datanascimento"),
                        resultSet.getString("cpfResponsavel"),
                        resultSet.getString("especie"),
                        resultSet.getString("raca"),
                        resultSet.getString("porte"),
                        resultSet.getString("Sexo"),
                        resultSet.getString("CaracteristicasFisicas"),
                        resultSet.getString("HistoricoDoencas"),
                        resultSet.getString("Medicacoes"));
            }
        } catch (SQLException e) {
            e.printStackTrace();  
        }
        return null;  
    }
}
