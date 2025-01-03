package model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "historico_rh")
public class HistoricoRh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cpf_rh")
    private String cpfRh;

    @Column(name = "acao")
    private String acao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_hora")
    private Date dataHora;

    public HistoricoRh() {
        this.dataHora = new Date();
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpfRh() {
        return cpfRh;
    }

    public void setCpfRh(String cpfRh) {
        this.cpfRh = cpfRh;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }
}
