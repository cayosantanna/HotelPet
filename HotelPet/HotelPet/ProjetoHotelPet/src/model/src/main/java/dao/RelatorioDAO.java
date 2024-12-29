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
import model.Relatorio;
import factory.Persistencia;

public class RelatorioDAO implements IDao<Relatorio> {
    private final String tabela = "relatorios";

    private String sql = "";

    @Override
    public List<Relatorio> findAll() {
        List<Relatorio> relatorios = new ArrayList<>();
        this.sql = "SELECT * FROM " + this.tabela;

        try (Connection connection = Persistencia.getConnection(); PreparedStatement statement = connection.prepareStatement(this.sql); ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Relatorio relatorio = new Relatorio(
                        resultSet.getInt("id"),
                        resultSet.getString("cpfUsuario"),
                        resultSet.getString("dataCheckIn"),
                        resultSet.getString("dataCheckOut"),
                        resultSet.getString("dataRealizacaoReserva"),
                        resultSet.getString("pet"),
                        resultSet.getDouble("valorPago"),
                        resultSet.getBoolean("checkBoxAlimentacaoEspecial"),
                        resultSet.getBoolean("checkBoxBanho"),
                        resultSet.getBoolean("checkBoxPasseio"),
                        resultSet.getBoolean("checkBoxTosa")
                );
                relatorios.add(relatorio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return relatorios;
    }

    @Override
    public void save(Relatorio relatorio) {
        this.sql = "INSERT INTO " + this.tabela + " (cpfUsuario, dataCheckIn, dataCheckOut, dataRealizacaoReserva, pet, valorPago, checkBoxAlimentacaoEspecial, checkBoxBanho, checkBoxPasseio, checkBoxTosa) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = Persistencia.getConnection(); PreparedStatement statement = connection.prepareStatement(this.sql)) {

            statement.setString(1, relatorio.getCpfUsuario());
            statement.setString(2, relatorio.getDataCheckIn());
            statement.setString(3, relatorio.getDataCheckOut());
            statement.setString(4, relatorio.getDataRealizacaoReserva());
            statement.setString(5, relatorio.getPet());
            statement.setDouble(6, relatorio.getValorPago());
            statement.setBoolean(7, relatorio.isCheckBoxAlimentacaoEspecial());
            statement.setBoolean(8, relatorio.isCheckBoxBanho());
            statement.setBoolean(9, relatorio.isCheckBoxPasseio());
            statement.setBoolean(10, relatorio.isCheckBoxTosa());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void update(Relatorio relatorio, Relatorio novo) {
        this.sql = "UPDATE " + this.tabela + " SET cpfUsuario = ?, dataCheckIn = ?, dataCheckOut = ?, dataRealizacaoReserva = ?, pet = ?, valorPago = ?, checkBoxAlimentacaoEspecial = ?, "
                + "checkBoxBanho = ?, checkBoxPasseio = ?, checkBoxTosa = ? WHERE id = ?";

        try (Connection connection = Persistencia.getConnection(); PreparedStatement statement = connection.prepareStatement(this.sql)) {
            statement.setString(1, novo.getCpfUsuario());
            statement.setString(2, novo.getDataCheckIn());
            statement.setString(3, novo.getDataCheckOut());
            statement.setString(4, novo.getDataRealizacaoReserva());
            statement.setString(5, novo.getPet());
            statement.setDouble(6, novo.getValorPago());
            statement.setBoolean(7, novo.isCheckBoxAlimentacaoEspecial());
            statement.setBoolean(8, novo.isCheckBoxBanho());
            statement.setBoolean(9, novo.isCheckBoxPasseio());
            statement.setBoolean(10, novo.isCheckBoxTosa());
            statement.setInt(11, relatorio.getId());  // Usando o ID do relatorio original para atualizar

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(Relatorio relatorio) {
        this.sql = "DELETE FROM " + this.tabela + " WHERE id = ?";

        try (Connection connection = Persistencia.getConnection(); PreparedStatement statement = connection.prepareStatement(this.sql)) {

            statement.setInt(1, relatorio.getId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Relatorio find(Relatorio relatorio) {
        this.sql = "SELECT * FROM " + this.tabela + " WHERE id = ?";

        try (Connection connection = Persistencia.getConnection(); PreparedStatement statement = connection.prepareStatement(this.sql)) {

            statement.setInt(1, relatorio.getId());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Relatorio(
                        resultSet.getInt("id"),
                        resultSet.getString("cpfUsuario"),
                        resultSet.getString("dataCheckIn"),
                        resultSet.getString("dataCheckOut"),
                        resultSet.getString("dataRealizacaoReserva"),
                        resultSet.getString("pet"),
                        resultSet.getDouble("valorPago"),
                        resultSet.getBoolean("checkBoxAlimentacaoEspecial"),
                        resultSet.getBoolean("checkBoxBanho"),
                        resultSet.getBoolean("checkBoxPasseio"),
                        resultSet.getBoolean("checkBoxTosa")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Relatorio findById(int id) {
        this.sql = "SELECT * FROM " + this.tabela + " WHERE id = ?";

        try (Connection connection = Persistencia.getConnection(); PreparedStatement statement = connection.prepareStatement(this.sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Relatorio(
                        resultSet.getInt("id"),
                        resultSet.getString("cpfUsuario"),
                        resultSet.getString("dataCheckIn"),
                        resultSet.getString("dataCheckOut"),
                        resultSet.getString("dataRealizacaoReserva"),
                        resultSet.getString("pet"),
                        resultSet.getDouble("valorPago"),
                        resultSet.getBoolean("checkBoxAlimentacaoEspecial"),
                        resultSet.getBoolean("checkBoxBanho"),
                        resultSet.getBoolean("checkBoxPasseio"),
                        resultSet.getBoolean("checkBoxTosa")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
