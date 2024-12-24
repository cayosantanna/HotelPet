package dao;

import factory.Persistencia;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import model.Pet;
import model.Reserva;

public class ReservaDAO implements IDao<Reserva> {

    protected Connection connection;
    private PreparedStatement statement;
    private String sql = "";

    private final String tabela = "pets";

    @Override
    public void save(Reserva reserva) {
        this.sql = "INSERT INTO " + this.tabela + " (servicoBanho, servicoTosa, servicoPasseio, servicoAlimentacaoEspecial, checkIn, checkOut, valorTotal, dataReserva) VALUES(?,?,?,?,?,?,?,?)";
        try {
            connection = Persistencia.getConnection();
            statement = connection.prepareStatement(this.sql);

            statement.setBoolean(1, reserva.isServicoBanho());
            statement.setBoolean(2, reserva.isServicoTosa());
            statement.setBoolean(3, reserva.isServicoPasseio());
            statement.setBoolean(4, reserva.isServicoAlimentacaoEspecial());
            statement.setDate(5, new Date(reserva.getCheckIn().getTime()));
            statement.setDate(6, new Date(reserva.getCheckOut().getTime()));
            statement.setDouble(7, reserva.getValorTotal());
            statement.setDate(8, new Date(reserva.getDataReserva().getTime()));

            statement.execute();
            statement.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        } finally {
            Persistencia.closeConnection();
        }
    }

    public void update(Reserva reserva) {
        this.sql = "UPDATE " + this.tabela + " SET servicoBanho=?, servicoTosa=?, servicoPasseio=?, servicoAlimentacaoEspecial=?, checkIn=?, checkOut=?, valorTotal=?, dataReserva=? WHERE id = ?";
        try {
            connection = Persistencia.getConnection();
            statement = connection.prepareStatement(this.sql);

            statement.setBoolean(1, reserva.isServicoBanho());
            statement.setBoolean(2, reserva.isServicoTosa());
            statement.setBoolean(3, reserva.isServicoPasseio());
            statement.setBoolean(4, reserva.isServicoAlimentacaoEspecial());
            statement.setDate(5, new Date(reserva.getCheckIn().getTime()));
            statement.setDate(6, new Date(reserva.getCheckOut().getTime()));
            statement.setDouble(7, reserva.getValorTotal());
            statement.setDate(8, new Date(reserva.getDataReserva().getTime()));
            statement.setInt(9, reserva.getId());

            statement.execute();
            statement.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        } finally {
            Persistencia.closeConnection();
        }
    }

    @Override
    public boolean delete(Reserva reserva) {
        this.sql = "DELETE FROM " + this.tabela + " WHERE id = ?";
        try {
            connection = Persistencia.getConnection();
            statement = connection.prepareStatement(this.sql);
            statement.setLong(1, reserva.getId());

            statement.execute();
            statement.close();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            Persistencia.closeConnection();
        }
    }

    @Override
    public Reserva find(Reserva reserva) {
        this.sql = "SELECT * FROM " + this.tabela + " WHERE id = ?";
        try {
            statement = Persistencia.getConnection().prepareStatement(sql);
            statement.setInt(1, reserva.getId());

            ResultSet resultset = statement.executeQuery();

            Reserva r = null;
            while (resultset.next()) {
                r = new Reserva(
                        resultset.getInt("id"),
                        resultset.getBoolean("servicoBanho"),
                        resultset.getBoolean("servicoTosa"),
                        resultset.getBoolean("servicoPasseio"),
                        resultset.getBoolean("servicoAlimentacaoEspecial"),
                        resultset.getDate("checkIn"),
                        resultset.getDate("checkOut"),
                        resultset.getDouble("valorTotal"),
                        resultset.getDate("dataReserva")
                );
            }
            statement.close();
            return r;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        } finally {
            Persistencia.closeConnection();
        }
    }

    @Override
    public List<Reserva> findAll() {
        List<Reserva> list = new ArrayList<>();
        this.sql = "SELECT * FROM " + this.tabela;
        try {
            connection = Persistencia.getConnection();
            statement = connection.prepareStatement(this.sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Reserva reserva = new Reserva(
                        resultSet.getInt("id"),
                        resultSet.getBoolean("servicoBanho"),
                        resultSet.getBoolean("servicoTosa"),
                        resultSet.getBoolean("servicoPasseio"),
                        resultSet.getBoolean("servicoAlimentacaoEspecial"),
                        resultSet.getDate("checkIn"),
                        resultSet.getDate("checkOut"),
                        resultSet.getDouble("valorTotal"),
                        resultSet.getDate("dataReserva")
                );
                list.add(reserva);
            }
            statement.close();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar todas as reservas: ", ex);
        } finally {
            Persistencia.closeConnection();
        }
        return list;
    }

    public Reserva findByNomePet(String nomePet) {
        return findAll().stream()
                .filter(reserva -> reserva.getNomePet().equalsIgnoreCase(nomePet))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void update(Reserva obj, Pet novo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Reserva> buscarPorNomeOuCpf(String nomePet, String cpfResponsavel) throws ParseException {
    List<Reserva> reservas = new ArrayList<>();
    StringBuilder sql = new StringBuilder("SELECT * FROM reservas r ");
    sql.append("JOIN pets p ON r.id_pet = p.id_pet ");
    sql.append("JOIN responsaveis resp ON r.id_responsavel = resp.id_responsavel ");
    sql.append("WHERE 1=1 ");
    
    // Adicionar condições baseadas nos parâmetros fornecidos
    if (nomePet != null && !nomePet.isEmpty()) {
        sql.append("AND p.nome LIKE ? ");
    }
    if (cpfResponsavel != null && !cpfResponsavel.isEmpty()) {
        sql.append("AND resp.cpf = ? ");
    }

    try (PreparedStatement stmt = connection.prepareStatement(sql.toString())) {
        int index = 1;
        
        // Preencher os parâmetros da consulta
        if (nomePet != null && !nomePet.isEmpty()) {
            stmt.setString(index++, "%" + nomePet + "%");
        }
        if (cpfResponsavel != null && !cpfResponsavel.isEmpty()) {
            stmt.setString(index++, cpfResponsavel);
        }
        
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Reserva reserva = new Reserva();
            reserva.setId(rs.getInt("id_reserva"));
            Pet pet = new Pet();
            pet.setNome(rs.getString("nome_pet"));
            reserva.setPet(pet);
            // Preencher os outros campos de Reserva conforme necessário
            reservas.add(reserva);
        }
    } catch (SQLException e) {
    }
    
    return reservas;
}

    
}

