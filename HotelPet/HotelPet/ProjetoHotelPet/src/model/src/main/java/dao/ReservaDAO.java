package dao;

import factory.Persistencia;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.Reserva;

/*public class ReservaDAO implements IDao {
    
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
    
}*/

public class ReservaDAO implements IDao<Reserva> {

    protected Connection connection;
    private PreparedStatement statement;
    private String sql;

    public ReservaDAO() {
        this.sql = "";
    }

    @Override
    public void save(Reserva reserva) {
        sql = "INSERT INTO reserva (servicoBanho, servicoTosa, servicoPasseio, servicoAlimentacaoEspecial, checkIn, checkOut, valorTotal, dataReserva) VALUES(?,?,?,?,?,?,?,?)";
        try {
            connection = Persistencia.getConnection();
            statement = connection.prepareStatement(sql);

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
        sql = "UPDATE reserva SET servicoBanho=?, servicoTosa=?, servicoPasseio=?, servicoAlimentacaoEspecial=?, checkIn=?, checkOut=?, valorTotal=?, dataReserva=? WHERE id = ?";
        try {
            connection = Persistencia.getConnection();
            statement = connection.prepareStatement(sql);

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
        sql = "DELETE FROM reserva WHERE id = ?";
        try {
            connection = Persistencia.getConnection();
            statement = connection.prepareStatement(sql);
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
        sql = "SELECT * FROM reserva WHERE id = ?";
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
        sql = "SELECT * FROM reserva"; 
        try {
            connection = Persistencia.getConnection();
            statement = connection.prepareStatement(sql);
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

}
