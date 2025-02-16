package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import factory.Persistencia;
import model.RelatorioFuncionario;

@Repository
public class RelatorioFuncionarioDAO implements IDao<RelatorioFuncionario> {
    
    @PersistenceContext
    private EntityManager em;

    private Connection connection;

    // Construtor para conectar ao banco de dados
    public RelatorioFuncionarioDAO() {
    }

    public void testConnection() {
    String sql = "SELECT 1";
    try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
        if (rs.next()) {
            System.out.println("Conexão com o banco de dados bem-sucedida.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    @Override
    public void save(RelatorioFuncionario obj) {
        EntityManager em = Persistencia.getEntityManager();
        try {
            em.getTransaction().begin();
            if (obj.getId() == 0) {
                em.persist(obj);
            } else {
                em.merge(obj);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void update(RelatorioFuncionario oldRelatorio, RelatorioFuncionario newRelatorio) {
        EntityManager em = Persistencia.getEntityManager();
        try {
            em.getTransaction().begin();
            
            // Converte as datas para java.util.Date
            if (newRelatorio.getDataEntrada() != null) {
                oldRelatorio.setDataEntrada(new java.util.Date(newRelatorio.getDataEntrada().getTime()));
            }
            if (newRelatorio.getDataSaida() != null) {
                oldRelatorio.setDataSaida(new java.util.Date(newRelatorio.getDataSaida().getTime()));
            }
            
            // Atualiza os outros campos
            oldRelatorio.setObservacoes(newRelatorio.getObservacoes());
            oldRelatorio.setRotinaEspecial(newRelatorio.getRotinaEspecial());
            oldRelatorio.setServicosExtras(newRelatorio.getServicosExtras());
            oldRelatorio.setStatusServico(newRelatorio.getStatusServico());
            oldRelatorio.setValorTotal(newRelatorio.getValorTotal());
            
            em.merge(oldRelatorio);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public boolean delete(RelatorioFuncionario obj) {
        String sql = "DELETE FROM relatorio_funcionario WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, obj.getId());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public RelatorioFuncionario find(RelatorioFuncionario obj) {
        String sql = "SELECT * FROM relatorio_funcionario WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, obj.getId());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new RelatorioFuncionario(
                        rs.getInt("id"),
                        rs.getString("cpf_responsavel"),
                        rs.getString("nome_pet"),
                        rs.getString("observacoes"),
                        rs.getBoolean("servico_banho"),
                        rs.getBoolean("servico_tosa"),
                        rs.getBoolean("servico_passeio"),
                        rs.getBoolean("servico_alimentacao_especial"),
                        rs.getString("rotina_especial"),
                        rs.getString("servicos_extras"),
                        rs.getDate("data_entrada"),
                        rs.getDate("data_saida"),
                        rs.getDouble("valor_total"),
                        rs.getString("status_servico")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<RelatorioFuncionario> findAll() {
        List<RelatorioFuncionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM relatorio_funcionario";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                RelatorioFuncionario funcionario = new RelatorioFuncionario(
                        rs.getInt("id"),
                        rs.getString("cpf_responsavel"),
                        rs.getString("nome_pet"),
                        rs.getString("observacoes"),
                        rs.getBoolean("servico_banho"),
                        rs.getBoolean("servico_tosa"),
                        rs.getBoolean("servico_passeio"),
                        rs.getBoolean("servico_alimentacao_especial"),
                        rs.getString("rotina_especial"),
                        rs.getString("servicos_extras"),
                        rs.getDate("data_entrada"),
                        rs.getDate("data_saida"),
                        rs.getDouble("valor_total"),
                        rs.getString("status_servico")
                );
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionarios;
    }

    public RelatorioFuncionario findById(Integer id) {
        EntityManager em = Persistencia.getEntityManager();
        try {
            return em.find(RelatorioFuncionario.class, id);
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
}
