package model;

import java.sql.Date;

public class RelatorioFuncionario {

    private int id;
    private String cpfResponsavel;
    private String nomePet;
    private String observacoes;
    private boolean ServicoBanho;
    private boolean ServicoTosa;
    private boolean ServicoPasseio;
    private boolean ServicoAlimentacaoEspecial;
    private String rotinaEspecial;
    private String ServicosExtras;
    private Date dataEntrada;
    private Date dataSaida;
    private double valorTotal;
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
        this.statusServico = statusServico;
    }
    
    public void calcularValorTotal() {
        double valor = 0.0;
        if (isServicoBanho()) valor += 50.0; 
        if (isServicoTosa()) valor += 40.0;
        if (isServicoPasseio()) valor += 30.0;
        if (isServicoAlimentacaoEspecial()) valor += 20.0;
        if (getRotinaEspecial() != null && !rotinaEspecial.isEmpty()) valor += 10.0;
        if (getServicosExtras() != null && !ServicosExtras.isEmpty()) valor += 15.0;

        this.setValorTotal(valor);
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the cpfResponsavel
     */
    public String getCpfResponsavel() {
        return cpfResponsavel;
    }

    /**
     * @param cpfResponsavel the cpfResponsavel to set
     */
    public void setCpfResponsavel(String cpfResponsavel) {
        this.cpfResponsavel = cpfResponsavel;
    }

    /**
     * @return the nomePet
     */
    public String getNomePet() {
        return nomePet;
    }

    /**
     * @param nomePet the nomePet to set
     */
    public void setNomePet(String nomePet) {
        this.nomePet = nomePet;
    }

    /**
     * @return the observacoes
     */
    public String getObservacoes() {
        return observacoes;
    }

    /**
     * @param observacoes the observacoes to set
     */
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    /**
     * @return the ServicoBanho
     */
    public boolean isServicoBanho() {
        return ServicoBanho;
    }

    /**
     * @param ServicoBanho the ServicoBanho to set
     */
    public void setServicoBanho(boolean ServicoBanho) {
        this.ServicoBanho = ServicoBanho;
    }

    /**
     * @return the ServicoTosa
     */
    public boolean isServicoTosa() {
        return ServicoTosa;
    }

    /**
     * @param ServicoTosa the ServicoTosa to set
     */
    public void setServicoTosa(boolean ServicoTosa) {
        this.ServicoTosa = ServicoTosa;
    }

    /**
     * @return the ServicoPasseio
     */
    public boolean isServicoPasseio() {
        return ServicoPasseio;
    }

    /**
     * @param ServicoPasseio the ServicoPasseio to set
     */
    public void setServicoPasseio(boolean ServicoPasseio) {
        this.ServicoPasseio = ServicoPasseio;
    }

    /**
     * @return the ServicoAlimentacaoEspecial
     */
    public boolean isServicoAlimentacaoEspecial() {
        return ServicoAlimentacaoEspecial;
    }

    /**
     * @param ServicoAlimentacaoEspecial the ServicoAlimentacaoEspecial to set
     */
    public void setServicoAlimentacaoEspecial(boolean ServicoAlimentacaoEspecial) {
        this.ServicoAlimentacaoEspecial = ServicoAlimentacaoEspecial;
    }

    /**
     * @return the rotinaEspecial
     */
    public String getRotinaEspecial() {
        return rotinaEspecial;
    }

    /**
     * @param rotinaEspecial the rotinaEspecial to set
     */
    public void setRotinaEspecial(String rotinaEspecial) {
        this.rotinaEspecial = rotinaEspecial;
    }

    /**
     * @return the ServicosExtras
     */
    public String getServicosExtras() {
        return ServicosExtras;
    }

    /**
     * @param ServicosExtras the ServicosExtras to set
     */
    public void setServicosExtras(String ServicosExtras) {
        this.ServicosExtras = ServicosExtras;
    }

    /**
     * @return the dataEntrada
     */
    public Date getDataEntrada() {
        return dataEntrada;
    }

    /**
     * @param dataEntrada the dataEntrada to set
     */
    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    /**
     * @return the dataSaida
     */
    public Date getDataSaida() {
        return dataSaida;
    }

    /**
     * @param dataSaida the dataSaida to set
     */
    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    /**
     * @return the valorTotal
     */
    public double getValorTotal() {
        return valorTotal;
    }

    /**
     * @param valorTotal the valorTotal to set
     */
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    /**
     * @return the statusServico
     */
    public String getStatusServico() {
        return statusServico;
    }

    /**
     * @param statusServico the statusServico to set
     */
    public void setStatusServico(String statusServico) {
        this.statusServico = statusServico;
    }


}
