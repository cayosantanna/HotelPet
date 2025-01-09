package model;

import javax.persistence.*;

@Entity
@Table(name = "historicoreserva")
public class HistoricoReserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Chave primária para a entidade

    @Column(nullable = false)
    private String nomePet;

    @Column(nullable = false)
    private String cpf;

    // Construtor
    public HistoricoReserva() {
        this.nomePet = "";
        this.cpf = "000.000.000-00";
    }

    // Método para copiar dados de outro histórico de reserva
    public void copiar(HistoricoReserva outro) {
        this.nomePet = outro.getNomePet();
        this.cpf = outro.getCpf();
    }

    // Getters e Setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomePet() {
        return nomePet;
    }

    public void setNomePet(String nomePet) {
        this.nomePet = nomePet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
