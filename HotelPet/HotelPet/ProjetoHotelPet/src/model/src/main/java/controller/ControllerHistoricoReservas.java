
package controller;
import model.HistoricoReserva;
import dao.HistoricoReservaDao;
import factory.Persistencia;
import java.util.List;
import javax.persistence.EntityManager;



/**
 *
 * @author neidi
 */
public class ControllerHistoricoReservas {

   
   private HistoricoReservaDao historicoReservaDao;

    public ControllerHistoricoReservas() {
        EntityManager em = Persistencia.getEntityManager();
        this.historicoReservaDao = new HistoricoReservaDao(em);
    }
   
   

    public List<HistoricoReserva> getAllHistoricoReservas() {
        return historicoReservaDao.findAll();
    }

    public HistoricoReserva findById(Long id) throws Exception {
        HistoricoReserva reserva = historicoReservaDao.findById(id);
        if (reserva == null) {
            throw new Exception("Histórico de reserva não encontrado.");
        }
        return reserva;
    }

    public List<HistoricoReserva> buscarReservasPorCpfOuNomePet(String cpfCliente, String nomePet) {
        return historicoReservaDao.findByCpfOrPetName(cpfCliente, nomePet);
    }

    public List<String> getHistoricoCompleto() {
        return historicoReservaDao.getHistorico();
    }

    public long contarReservasPorPet(String nomePet) {
        return historicoReservaDao.countReservasPorPet(nomePet);
    }

    public long contarReservasPorCliente(String cpfCliente) {
        return historicoReservaDao.countReservasPorCliente(cpfCliente);
    }
   

   

   
}
