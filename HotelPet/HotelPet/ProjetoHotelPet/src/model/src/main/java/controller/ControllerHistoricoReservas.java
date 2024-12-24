
package controller;
import model.HistoricoReserva;
import dao.HistoricoReservaDao;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.TableModel;


/**
 *
 * @author neidi
 */
public class ControllerHistoricoReservas {

    private HistoricoReservaDao repositorio;

    // Construtor inicializando o repositório
    public ControllerHistoricoReservas() {
        this.repositorio = new HistoricoReservaDao();
    }

    // Busca um histórico de reserva pelo CPF
    public HistoricoReserva buscarHistoricoReserva(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser nulo ou vazio.");
        }
        return this.repositorio.findByCpf(cpf);
    }
    
    public List<HistoricoReserva> buscarHistoricoReservaCpfAndPetName(String cpf, String petName) {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser nulo ou vazio.");
        }
        if(petName == null || petName.trim().isEmpty()){
            throw new IllegalArgumentException("CPF não pode ser nulo ou vazio.");
        }
        return this.repositorio.getByCpfandPetName(cpf, petName);
    }

    // Atualiza a tabela com os dados do histórico de reservas
    public void atualizarTabela(JTable grd) {
        if (grd == null) {
            throw new IllegalArgumentException("A tabela não pode ser nula.");
        }

        // Supõe-se que TMHistoricoReservas é um TableModel válido
        TableModel modeloTabela = new TMHistoricoReservas(repositorio.findAll());
        grd.setModel(modeloTabela);
    }
}
