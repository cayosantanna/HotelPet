package model;

import javax.persistence.*;

@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Gerador automático para a chave primária
    private Long id;  // Adicionando o identificador único

    @ManyToOne
    @JoinColumn(name = "reserva_id", referencedColumnName = "id", nullable = false)
    private Reserva reserva;

    @Column(nullable = false)
    private String metodoPagamento;

    @Column(nullable = false)
    private int parcelas;

    // Construtor
    public Pagamento(Reserva reserva, String metodoPagamento, int parcelas) {
        this.reserva = reserva;
        this.metodoPagamento = metodoPagamento;
        this.parcelas = parcelas;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }
}
