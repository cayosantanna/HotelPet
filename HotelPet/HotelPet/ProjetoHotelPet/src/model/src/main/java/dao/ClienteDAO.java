package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import factory.Persistencia;
import model.Pet;

public class ClienteDAO implements IDao<Cliente> {

    private final String tabela = "clientes";

    private String sql = "";

    @Override
    public List<Cliente> findAll() {
        List<Cliente> clientes = new ArrayList<>();
        this.sql = "SELECT * FROM " + this.tabela;
        try (Connection connection = Persistencia.getConnection(); PreparedStatement statement = connection.prepareStatement(this.sql); ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Cliente cliente = new Cliente(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("cpf"),
                        resultSet.getString("email"),
                        resultSet.getString("telefone"),
                        resultSet.getString("endereco"),
                        resultSet.getString("cep"),
                        resultSet.getString("senha")
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    @Override
    public Cliente find(Cliente obj) {
        this.sql = "SELECT * FROM " + this.tabela + " WHERE id = ?";
        try (Connection connection = Persistencia.getConnection(); PreparedStatement statement = connection.prepareStatement(this.sql)) {

            statement.setInt(1, obj.getId());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Cliente(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("cpf"),
                        resultSet.getString("email"),
                        resultSet.getString("telefone"),
                        resultSet.getString("endereco"),
                        resultSet.getString("cep"),
                        resultSet.getString("senha")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Cliente obj) {
        this.sql = "INSERT INTO " + this.tabela + " (nome, cpf, email, telefone) VALUES (?, ?, ?, ?)";
        try (Connection connection = Persistencia.getConnection(); PreparedStatement statement = connection.prepareStatement(this.sql)) {

            statement.setString(1, obj.getNome());
            statement.setString(2, obj.getCpf());
            statement.setString(3, obj.getEmail());
            statement.setString(4, obj.getTelefone());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Cliente obj, Cliente Novo) {
        this.sql = "UPDATE " + this.tabela + " SET nome = ?, cpf = ?, email = ?, telefone = ? WHERE id = ?";
        try (Connection connection = Persistencia.getConnection(); PreparedStatement statement = connection.prepareStatement(this.sql)) {

            statement.setString(1, Novo.getNome());
            statement.setString(2, Novo.getCpf());
            statement.setString(3, Novo.getEmail());
            statement.setString(4, Novo.getTelefone());
            statement.setInt(5, obj.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(Cliente obj) {
        this.sql = "DELETE FROM " + this.tabela + " WHERE id = ?";
        try (Connection connection = Persistencia.getConnection(); PreparedStatement statement = connection.prepareStatement(this.sql)) {

            statement.setInt(1, obj.getId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Cliente findByCPF(String cpf) {
        return findAll().stream()
                .filter(cliente -> cliente.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);
    }

    public void update(Cliente obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Cliente findById(int id) {
        this.sql = "SELECT * FROM " + this.tabela + " WHERE id = ?";
        try (Connection connection = Persistencia.getConnection(); PreparedStatement statement = connection.prepareStatement(this.sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Cliente(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("cpf"),
                        resultSet.getString("email"),
                        resultSet.getString("telefone"),
                        resultSet.getString("endereco"),
                        resultSet.getString("cep"),
                        resultSet.getString("senha")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Cliente obj, Pet novo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
