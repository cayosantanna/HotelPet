
package controller;
import model.HistoricoReserva;
import dao.HistoricoReservaDao;
import javax.swing.JTable;
import jdk.jshell.execution.Util;

/**
 *
 * @author neidi
 */
public class ControllerHistoricoReservas {

    private HistoricoReservaDao repositorio;
    
     public ControllerHistoricoReservas() {
        repositorio = new HistoricoReservaDao();
    }

     public HistoricoReserva buscarHistoricoReserva(String cpf) {
        return this.repositorio.findByCpf(cpf);
    }

    public void atualizarTabela(JTable grd) {
        Util.jTableShow(grd, new TMHistoricoReservas(repositorio.findAll()), null);
    }
}
