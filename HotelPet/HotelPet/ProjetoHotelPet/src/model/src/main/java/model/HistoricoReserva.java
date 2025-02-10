package model;


public class HistoricoReserva {


    private Long id;  
    private String nomePet;
    private String cpf;
    public HistoricoReserva() {
        this.nomePet = "";
        this.cpf = "000.000.000-00";
    }

    // Método para copiar dados de outro histórico de reserva
    public void copiar(HistoricoReserva outro) {
        this.nomePet = outro.getNomePet();
        this.cpf = outro.getCpf();
    }

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
