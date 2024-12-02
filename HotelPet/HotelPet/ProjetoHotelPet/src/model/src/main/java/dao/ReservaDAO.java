/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import factory.Persistencia;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Reserva;

/**
 *
 * @author thais
 */
public class ReservaDAO implements IDao {
    
    protected Connection connection;
    private PreparedStatement statement;
    private String sql;

    public ReservaDAO() {
        this.sql = "";
    }

    @Override
    public void save(Object obj) {
        Reserva reserva = (Reserva) obj;

        sql = " INSERT INTO "
                + " reserva(servicoBanho, servicoTosa, servicoPasseio, servicoAlimentacaoEspecial, servicoAlimentacaoEspecial, checkIn, checkOut, valorTotal, dataReserva) "
                + " VALUES(?,?,?,?,?,?,?,?) ";
        try {
            connection = Persistencia.getConnection();
            statement = connection.prepareStatement(sql);

            //preencher cada ? com o campo adequado
            statement.setBoolean(1, reserva.isServicoBanho());
            statement.setBoolean(2, reserva.isServicoTosa());
            statement.setBoolean(3, reserva.isServicoPasseio());
            statement.setBoolean(4, reserva.isServicoAlimentacaoEspecial());
            statement.setDate(5, (Date) reserva.getCheckIn());
            statement.setDate(6, (Date) reserva.getCheckOut());
            statement.setDouble(7, reserva.getValorTotal());
            statement.setDate(8, (Date) reserva.getDataReserva());

            statement.execute();
            statement.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        } finally {
            Persistencia.closeConnection();
        }
    }

    @Override
    public void update(Object obj) {
        Reserva reserva = (Reserva) obj;

        sql = " UPDATE reserva "
                + " SET servicoBanho=?, servicoTosa=?, servicoPasseio=?, servicoAlimentacaoEspecial=?, checkIn=?, checkOut=?, valorTotal=?, dataReserva=? "
                + " WHERE id = ?";
        try {
            connection = Persistencia.getConnection();
            statement = connection.prepareStatement(sql);

            //preencher cada ? com o campo adequado
           statement.setBoolean(1, reserva.isServicoBanho());
            statement.setBoolean(2, reserva.isServicoTosa());
            statement.setBoolean(3, reserva.isServicoPasseio());
            statement.setBoolean(4, reserva.isServicoAlimentacaoEspecial());
            statement.setDate(5, (Date) reserva.getCheckIn());
            statement.setDate(6, (Date) reserva.getCheckOut());
            statement.setDouble(7, reserva.getValorTotal());
            statement.setDate(8, (Date) reserva.getDataReserva());

            //preenche a condição do WHERE
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
    public boolean delete(Object obj) {
        Reserva reserva = (Reserva) obj;

        sql = " DELETE FROM reserva WHERE id = ? ";
        try {
            connection = Persistencia.getConnection();
            statement = connection.prepareStatement(sql);
            //preenche a condição
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
    public Object find(Object obj) {
        Reserva reserva = (Reserva) obj;

        sql = " SELECT * FROM reserva WHERE id = ? ";
        try {

            statement = Persistencia.getConnection().prepareStatement(sql);
            statement.setInt(1, reserva.getId());

            ResultSet resultset = statement.executeQuery();

            Reserva r = null;
            while (resultset.next()) {
                r = new Reserva(
                        resultset.getInt(1),
                        resultset.getBoolean(2),
                        resultset.getBoolean(3),
                        resultset.getBoolean(4),
                        resultset.getBoolean(5),
                        resultset.getDate(6),
                        resultset.getDate(7),
                        resultset.getDouble(8),
                        resultset.getDate(9));
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
    public List<Object> findAll() {
        List<Object> list = new ArrayList<>();

        sql = " SELECT * FROM reserva ORDER BY upper(nome) ";
        try {
            statement = Persistencia.getConnection().prepareStatement(sql);
            ResultSet resultset = statement.executeQuery();
            while (resultset.next()) {
                Reserva reserva = new Reserva(
                        resultset.getInt(1),
                        resultset.getBoolean(2),
                        resultset.getBoolean(3),
                        resultset.getBoolean(4),
                        resultset.getBoolean(5),
                        resultset.getDate(6),
                        resultset.getDate(7),
                        resultset.getDouble(8),
                        resultset.getDate(9));

                list.add(reserva);
            }
            statement.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            Persistencia.closeConnection();
        }

        return list;
    }
    
}
