package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.RelatorioFuncionario;


public class RelatorioFuncionarioDAO implements IDao<RelatorioFuncionario> {
    

    private final Connection connection;

    // Construtor para conectar ao banco de dados
    public RelatorioFuncionarioDAO(Connection connection) {
        this.connection = connection;
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
        String sql = "INSERT INTO relatorio_funcionario (cpf_responsavel, nome_pet, observacoes, servico_banho, servico_tosa, servico_passeio, " +
                     "servico_alimentacao_especial, rotina_especial, servicos_extras, data_entrada, data_saida, valor_total, status_servico) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, obj.getCpfResponsavel());
            stmt.setString(2, obj.getNomePet());
            stmt.setString(3, obj.getObservacoes());
            stmt.setBoolean(4, obj.isServicoBanho());
            stmt.setBoolean(5, obj.isServicoTosa());
            stmt.setBoolean(6, obj.isServicoPasseio());
            stmt.setBoolean(7, obj.isServicoAlimentacaoEspecial());
            stmt.setString(8, obj.getRotinaEspecial());
            stmt.setString(9, obj.getServicosExtras());
            stmt.setDate(10, obj.getDataEntrada());
            stmt.setDate(11, obj.getDataSaida());
            stmt.setDouble(12, obj.getValorTotal());
            stmt.setString(13, obj.getStatusServico());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(RelatorioFuncionario antigo, RelatorioFuncionario novo) {
        String sql = "UPDATE relatorio_funcionario SET " +
                    "observacoes = ?, servicos_extras = ?, rotina_especial = ?, " +
                    "valor_total = ?, status_servico = ?, data_saida = ?, " +
                    "finalizado = ? " +
                    "WHERE id = ?";
                    
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, novo.getObservacoes());
            stmt.setString(2, novo.getServicosExtras());
            stmt.setString(3, novo.getRotinaEspecial());
            stmt.setDouble(4, novo.getValorTotal());
            stmt.setString(5, novo.getStatusServico());
            stmt.setDate(6, novo.getDataSaida());
            stmt.setBoolean(7, novo.isFinalizado());
            stmt.setInt(8, antigo.getId());
            
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("Nenhuma linha foi atualizada.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar relatório: " + e.getMessage());
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
}
