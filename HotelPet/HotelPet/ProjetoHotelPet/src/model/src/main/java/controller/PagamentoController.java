package controller;

import dao.PagamentoDAO;
import model.Pagamento;
import model.Reserva;

public class PagamentoController {
    private PagamentoDAO pagamentoDAO;

    public PagamentoController() {
        this.pagamentoDAO = new PagamentoDAO();
    }

    public void salvarPagamento(Reserva reserva, String metodoPagamento, int parcelas) {
        Pagamento pagamento = new Pagamento(reserva, metodoPagamento, parcelas);
        pagamentoDAO.save(pagamento);
    }
}
