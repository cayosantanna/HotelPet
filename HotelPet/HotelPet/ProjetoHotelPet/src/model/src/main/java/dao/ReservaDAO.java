/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import factory.Persistencia;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object find(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Object> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
