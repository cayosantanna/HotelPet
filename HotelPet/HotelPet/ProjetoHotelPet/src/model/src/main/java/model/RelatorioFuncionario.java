package model;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
 *
 * @author thais
 */
@Data
@Entity
@Table(name = "relatorio_funcionario")
@NamedQueries({
    @NamedQuery(name = "RelatorioFuncionario.findById",
            query = "SELECT r FROM RelatorioFuncionario r WHERE r.id = :id"),
    // ...existing named queries...
})
public class RelatorioFuncionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String cpfResponsavel;

    @Column(nullable = false)
    private String nomePet;

    @Column(length = 1000)
    private String observacoes;

    private boolean servicoBanho;

    private boolean servicoTosa;

    private boolean servicoPasseio;

    private boolean servicoAlimentacaoEspecial;

    @Column(length = 500)
    private String rotinaEspecial;

    @Column(length = 500)
    private String servicosExtras;

    @Temporal(TemporalType.DATE)
    private Date dataEntrada;

    @Temporal(TemporalType.DATE)
    private Date dataSaida;

    private Double valorTotal;

    private String statusServico;

    @ManyToOne
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;

    // Construtores, getters e setters

    public RelatorioFuncionario() {
    }

    public RelatorioFuncionario(int id, String cpfResponsavel, String nomePet, String observacoes,
            boolean servicoBanho, boolean servicoTosa, boolean servicoPasseio, boolean servicoAlimentacaoEspecial,
            String rotinaEspecial, String servicosExtras, Date dataEntrada, Date dataSaida, double valorTotal, String statusServico) {
        this.id = id;
        this.cpfResponsavel = cpfResponsavel;
        this.nomePet = nomePet;
        this.observacoes = observacoes;
        this.servicoBanho = servicoBanho;
        this.servicoTosa = servicoTosa;
        this.servicoPasseio = servicoPasseio;
        this.servicoAlimentacaoEspecial = servicoAlimentacaoEspecial;
        this.rotinaEspecial = rotinaEspecial;
        this.servicosExtras = servicosExtras;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.valorTotal = valorTotal;
        this.statusServico = "Em Andamento";
    }

    public void calcularValorTotal() {
        double valorServicos = 0.0;

        // Soma dos valores dos serviços selecionados
        if (servicoBanho) valorServicos += 90.0;
        if (servicoTosa) valorServicos += 70.0;
        if (servicoPasseio) valorServicos += 60.0;
        if (servicoAlimentacaoEspecial) valorServicos += 100.0;

        long dias = 0;

        // Verifica se as datas de entrada e saída são válidas
        if (dataEntrada != null && dataSaida != null) {
            long diff = dataSaida.getTime() - dataEntrada.getTime();
            dias = diff / (1000 * 60 * 60 * 24); // Diferença em dias
        }

        // Calcula o valor total
        this.valorTotal = dias * 75.0 + valorServicos; // Exemplo: R$75 por dia de estadia
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpfResponsavel() {
        return cpfResponsavel;
    }

    public void setCpfResponsavel(String cpfResponsavel) {
        this.cpfResponsavel = cpfResponsavel;
    }

    public String getNomePet() {
        return nomePet;
    }

    public void setNomePet(String nomePet) {
        this.nomePet = nomePet;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public boolean isServicoBanho() {
        return servicoBanho;
    }

    public void setServicoBanho(boolean servicoBanho) {
        this.servicoBanho = servicoBanho;
    }

    public boolean isServicoTosa() {
        return servicoTosa;
    }

    public void setServicoTosa(boolean servicoTosa) {
        this.servicoTosa = servicoTosa;
    }

    public boolean isServicoPasseio() {
        return servicoPasseio;
    }

    public void setServicoPasseio(boolean servicoPasseio) {
        this.servicoPasseio = servicoPasseio;
    }

    public boolean isServicoAlimentacaoEspecial() {
        return servicoAlimentacaoEspecial;
    }

    public void setServicoAlimentacaoEspecial(boolean servicoAlimentacaoEspecial) {
        this.servicoAlimentacaoEspecial = servicoAlimentacaoEspecial;
    }

    public String getRotinaEspecial() {
        return rotinaEspecial;
    }

    public void setRotinaEspecial(String rotinaEspecial) {
        this.rotinaEspecial = rotinaEspecial;
    }

    public String getServicosExtras() {
        return servicosExtras;
    }

    public void setServicosExtras(String servicosExtras) {
        this.servicosExtras = servicosExtras;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        if (dataEntrada instanceof java.sql.Date) {
            this.dataEntrada = new Date(dataEntrada.getTime());
        } else {
            this.dataEntrada = dataEntrada;
        }
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        if (dataSaida instanceof java.sql.Date) {
            this.dataSaida = new Date(dataSaida.getTime());
        } else {
            this.dataSaida = dataSaida;
        }
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getStatusServico() {
        return statusServico;
    }

    public void setStatusServico(String statusServico) {
        this.statusServico = statusServico;
    }

    public boolean isFinalizado() {
        return "Finalizado".equals(this.statusServico);
    }

    public void setFinalizado(boolean finalizado) {
        this.statusServico = finalizado ? "Finalizado" : "Em Andamento";
    }
}
