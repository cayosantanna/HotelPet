/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;

public class ClienteDAOSimulado implements IDao<Cliente> {
    private static final String FILE_PATH = "clientes.txt";

    @Override
    public List<Cliente> findAll() {
        List<Cliente> clientes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                Cliente cliente = new Cliente(
                        Integer.parseInt(data[0]), 
                        data[1],
                        data[2], 
                        data[3], 
                        data[4], 
                        data[5], 
                        data[6], 
                        data[7]  
                );
                clientes.add(cliente);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    @Override
    public Cliente find(Cliente obj) {
        return findAll().stream()
                .filter(cliente -> cliente.getId() == obj.getId())
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(Cliente obj) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(obj.getId() + ";" + obj.getNome() + ";" + obj.getCpf() + ";" + obj.getEmail() + ";" +
                         obj.getTelefone() + ";" + obj.getEndereco() + ";" + obj.getCep() + ";" + obj.getSenha());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(Cliente obj, Cliente novo) {
        List<Cliente> clientes = findAll();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Cliente cliente : clientes) {
                if (cliente.getId() == obj.getId()) {
                    cliente = novo; // Atualiza os dados
                }
                writer.write(cliente.getId() + ";" + cliente.getNome() + ";" + cliente.getCpf() + ";" +
                             cliente.getEmail() + ";" + cliente.getTelefone() + ";" + cliente.getEndereco() +
                             ";" + cliente.getCep() + ";" + cliente.getSenha());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(Cliente obj) {
        List<Cliente> clientes = findAll();
        boolean removed = clientes.removeIf(cliente -> cliente.getId() == obj.getId());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Cliente cliente : clientes) {
                writer.write(cliente.getId() + ";" + cliente.getNome() + ";" + cliente.getCpf() + ";" +
                             cliente.getEmail() + ";" + cliente.getTelefone() + ";" + cliente.getEndereco() +
                             ";" + cliente.getCep() + ";" + cliente.getSenha());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return removed;
    }

    @Override
    public void update(Cliente obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Cliente findByCPF(String cpf) {
        return findAll().stream()
                .filter(cliente -> cliente.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);
    }
}
