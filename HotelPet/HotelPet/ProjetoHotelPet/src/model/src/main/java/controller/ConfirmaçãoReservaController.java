package controller;

import dao.ConfirmaçãoReservaDAO;
import model.ConfirmaçãoReserva;
import model.exceptions.ReservaException;
import java.util.List;

/**
 * Controlador para manipulação dos relatórios.
 */
public class ConfirmaçãoReservaController {
    private ConfirmaçãoReservaDAO relatorioDAO;

    public ConfirmaçãoReservaController(ConfirmaçãoReservaDAO relatorioDAO) {
        this.relatorioDAO = relatorioDAO;
    }

    public void cadastrarRelatorio(ConfirmaçãoReserva relatorio) throws ReservaException {
        try {
            relatorioDAO.save(relatorio);
        } catch (Exception e) {
            throw new ReservaException("Erro ao cadastrar relatório", e);
        }
    }


    public List<ConfirmaçãoReserva> listarTodosRelatorios() {
        return relatorioDAO.findAll();
    }

    /**
     * Atualiza um relatório existente.
     * @param relatorio Relatório a ser atualizado.
     * @param novo Relatório com os novos dados.
     * @throws ReservaException Exceção lançada em caso de erro durante a atualização.
     */
    public void atualizarRelatorio(ConfirmaçãoReserva relatorio, ConfirmaçãoReserva novo) throws ReservaException {
        try {
            relatorioDAO.update(relatorio, novo);
        } catch (Exception e) {
            throw new ReservaException("Erro ao atualizar relatório", e);
        }
    }


    public void excluirRelatorio(ConfirmaçãoReserva relatorio) throws ReservaException {
        boolean sucesso = relatorioDAO.delete(relatorio);
        if (!sucesso) {
            throw new ReservaException("Erro ao excluir relatório: Relatório não encontrado.");
        }
    }


    public ConfirmaçãoReserva findById(int id) {
        return relatorioDAO.findById(id);
    }
}
