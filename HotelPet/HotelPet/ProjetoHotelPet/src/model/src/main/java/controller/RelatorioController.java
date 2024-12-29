/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.RelatorioDAO;
import java.util.List;
import model.Relatorio;
import model.exceptions.ReservaException;

/**
 *
 * @author thais
 */
public class RelatorioController {
    private RelatorioDAO RelatorioDAO;
    private boolean usarSimulador;

    public RelatorioController(boolean usarSimulador) {
        this.usarSimulador = usarSimulador;
        if (usarSimulador) {
            this.RelatorioDAO = new RelatorioDAO();
        } else {
            this.RelatorioDAO = new RelatorioDAO();
        }
    }

    public void cadastrarRelatorio(Relatorio Relatorio) throws ReservaException {
        if (usarSimulador) {
            RelatorioDAO.save(Relatorio);
        } else {
            RelatorioDAO.save(Relatorio);
        }
    }

    public List<Relatorio> listarTodosRelatorios() {
        return usarSimulador ? RelatorioDAO.findAll() : RelatorioDAO.findAll();
    }

    public void atualizarRelatorio(Relatorio relatorio, Relatorio novo) throws ReservaException {
        RelatorioDAO relatorioDAO = new RelatorioDAO(); // Instancia o DAO

     if (usarSimulador) {
            // Passa ambos os objetos relatorio e novo para o método update
            relatorioDAO.update(relatorio, novo);  // Chamada com dois parâmetros
        } else {
        // Passa ambos os objetos relatorio e novo para o método update
            relatorioDAO.update(relatorio, novo);  // Chamada com dois parâmetros
        }
    }





    public void excluirRelatorio(Relatorio Relatorio) {
        if (usarSimulador) {
            RelatorioDAO.delete(Relatorio);
        } else {
            RelatorioDAO.delete(Relatorio);
        }
    }
    
     public Relatorio findById(int id) {
        return this.RelatorioDAO.findById(id);
    }
}
