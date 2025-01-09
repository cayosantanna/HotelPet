package model;

import javax.persistence.*;
import java.sql.Date;

/**
 *
 * @author thais
 */
@Entity
@Table(name = "relatoriofuncionario")
public class RelatorioFuncionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String cpfResponsavel;

    @Column(nullable = false)
    private String nomePet;

    private String observacoes;

    @Column(nullable = false)
    private boolean ServicoBanho;

    @Column(nullable = false)
    private boolean ServicoTosa;

    @Column(nullable = false)
    private boolean ServicoPasseio;

    @Column(nullable = false)
    private boolean ServicoAlimentacaoEspecial;

    private String rotinaEspecial;
    private String ServicosExtras;

    @Column(nullable = false)
    private Date dataEntrada;

    @Column(nullable = false)
    private Date dataSaida;

    @Column(nullable = false)
    private double valorTotal;

    @Column(nullable = false)
    private String statusServico;

    // Construtores, getters e setters

    public RelatorioFuncionario() {
    }

    public RelatorioFuncionario(int id, String cpfResponsavel, String nomePet, String observacoes,
            boolean ServicoBanho, boolean ServicoTosa, boolean ServicoPasseio, boolean ServicoAlimentacaoEspecial,
            String rotinaEspecial, String ServicosExtras, Date dataEntrada, Date dataSaida, double valorTotal, String statusServico) {
        this.id = id;
        this.cpfResponsavel = cpfResponsavel;
        this.nomePet = nomePet;
        this.observacoes = observacoes;
        this.ServicoBanho = ServicoBanho;
        this.ServicoTosa = ServicoTosa;
        this.ServicoPasseio = ServicoPasseio;
        this.ServicoAlimentacaoEspecial = ServicoAlimentacaoEspecial;
        this.rotinaEspecial = rotinaEspecial;
        this.ServicosExtras = ServicosExtras;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.valorTotal = valorTotal;
        this.statusServico = "Em Andamento";
    }

    public void calcularValorTotal() {
        double valorServicos = 0.0;

        // Soma dos valores dos serviços selecionados
        if (ServicoBanho) valorServicos += 90.0;
        if (ServicoTosa) valorServicos += 70.0;
        if (ServicoPasseio) valorServicos += 60.0;
        if (ServicoAlimentacaoEspecial) valorServicos += 100.0;

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
        return ServicoBanho;
    }

    public void setServicoBanho(boolean ServicoBanho) {
        this.ServicoBanho = ServicoBanho;
    }

    public boolean isServicoTosa() {
        return ServicoTosa;
    }

    public void setServicoTosa(boolean ServicoTosa) {
        this.ServicoTosa = ServicoTosa;
    }

    public boolean isServicoPasseio() {
        return ServicoPasseio;
    }

    public void setServicoPasseio(boolean ServicoPasseio) {
        this.ServicoPasseio = ServicoPasseio;
    }

    public boolean isServicoAlimentacaoEspecial() {
        return ServicoAlimentacaoEspecial;
    }

    public void setServicoAlimentacaoEspecial(boolean ServicoAlimentacaoEspecial) {
        this.ServicoAlimentacaoEspecial = ServicoAlimentacaoEspecial;
    }

    public String getRotinaEspecial() {
        return rotinaEspecial;
    }

    public void setRotinaEspecial(String rotinaEspecial) {
        this.rotinaEspecial = rotinaEspecial;
    }

    public String getServicosExtras() {
        return ServicosExtras;
    }

    public void setServicosExtras(String ServicosExtras) {
        this.ServicosExtras = ServicosExtras;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
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
