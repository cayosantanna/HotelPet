package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "reserva")
@NamedQuery(
    name = "Reserva.findAll",
    query = "SELECT r FROM Reserva r LEFT JOIN FETCH r.cliente LEFT JOIN FETCH r.pet"
)
public class Reserva implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Cliente cliente; // Cliente associado à reserva

    @ManyToOne
    private Pet pet; // Pet associado à reserva

    private boolean servicoBanho;
    private boolean servicoTosa;
    private boolean servicoPasseio;
    private boolean servicoAlimentacaoEspecial;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date checkIn;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date checkOut;
  
    private double valorTotal;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataReserva;

    private String descricaoServicosExtras;
    private String statusServico;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "relatorio_funcionario_id")
    private RelatorioFuncionario relatorioFuncionario;

    public Reserva() {
        // Construtor padrão
    }

    public Reserva(Cliente cliente, Pet pet, boolean servicoBanho, boolean servicoTosa, boolean servicoPasseio,
        boolean servicoAlimentacaoEspecial, Date checkIn, Date checkOut, Date dataReserva, String statusServico) {
        this.cliente = cliente;
        this.pet = pet;
        this.servicoBanho = servicoBanho;
        this.servicoTosa = servicoTosa;
        this.servicoPasseio = servicoPasseio;
        this.servicoAlimentacaoEspecial = servicoAlimentacaoEspecial;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.dataReserva = dataReserva;
        this.statusServico = statusServico;
        calcularValorTotal();
    }

    private void calcularValorTotal() {
        double valorServicos = 0.0;

        if (servicoBanho) valorServicos += 90.0; // Exemplo de valor para banho
        if (servicoTosa) valorServicos += 70.0; // Exemplo de valor para tosa
        if (servicoPasseio) valorServicos += 60.0; // Exemplo de valor para passeio
        if (servicoAlimentacaoEspecial) valorServicos += 100.0; // Exemplo de valor para alimentação especial

        // Calcular o valor total baseado na duração da reserva
        long diff = checkOut.getTime() - checkIn.getTime();
        long dias = diff / (1000 * 60 * 60 * 24); // Diferença em dias

        this.valorTotal = dias * 75.0 + valorServicos; // Exemplo de valor fixo por dia
    }

    // Getters e Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
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

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Date getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(Date dataReserva) {
        this.dataReserva = dataReserva;
    }

    public String getDescricaoServicosExtras() {
        return descricaoServicosExtras;
    }

    public void setDescricaoServicosExtras(String descricaoServicosExtras) {
        this.descricaoServicosExtras = descricaoServicosExtras;
    }
    public boolean isFinalizado() {
    return "Finalizado".equals(this.statusServico);
}

    public void setFinalizado(boolean finalizado) {
    this.statusServico = finalizado ? "Finalizado" : "Em Andamento";
    }

    public RelatorioFuncionario getRelatorioFuncionario() {
        return this.relatorioFuncionario;
    }

    public void setRelatorioFuncionario(RelatorioFuncionario relatorioFuncionario) {
        this.relatorioFuncionario = relatorioFuncionario;
    }

}
