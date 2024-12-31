/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.PagamentoDAO;
import model.Pagamento;
import model.Reserva;

/**
 *
 * @author thais
 */
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
