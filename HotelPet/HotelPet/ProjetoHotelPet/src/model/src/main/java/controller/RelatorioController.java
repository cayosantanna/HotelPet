package controller;

import dao.RelatorioDAO;
import model.Relatorio;
import model.exceptions.ReservaException;
import java.util.List;

/**
 * Controlador para manipulação dos relatórios.
 */
public class RelatorioController {
    private RelatorioDAO relatorioDAO;

    public RelatorioController(RelatorioDAO relatorioDAO) {
        this.relatorioDAO = relatorioDAO;
    }

    public void cadastrarRelatorio(Relatorio relatorio) throws ReservaException {
        try {
            relatorioDAO.save(relatorio);
        } catch (Exception e) {
            throw new ReservaException("Erro ao cadastrar relatório", e);
        }
    }


    public List<Relatorio> listarTodosRelatorios() {
        return relatorioDAO.findAll();
    }

    /**
     * Atualiza um relatório existente.
     * @param relatorio Relatório a ser atualizado.
     * @param novo Relatório com os novos dados.
     * @throws ReservaException Exceção lançada em caso de erro durante a atualização.
     */
    public void atualizarRelatorio(Relatorio relatorio, Relatorio novo) throws ReservaException {
        try {
            relatorioDAO.update(relatorio, novo);
        } catch (Exception e) {
            throw new ReservaException("Erro ao atualizar relatório", e);
        }
    }


    public void excluirRelatorio(Relatorio relatorio) throws ReservaException {
        boolean sucesso = relatorioDAO.delete(relatorio);
        if (!sucesso) {
            throw new ReservaException("Erro ao excluir relatório: Relatório não encontrado.");
        }
    }


    public Relatorio findById(int id) {
        return relatorioDAO.findById(id);
    }
}
