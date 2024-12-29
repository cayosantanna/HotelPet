
package model;

public class Relatorio {
    private int id;
    private String cpfUsuario;
    private String dataCheckIn;
    private String dataCheckOut;
    private String dataRealizacaoReserva;
    private String pet;
    private double valorPago;
    private boolean checkBoxAlimentacaoEspecial;
    private boolean checkBoxBanho;
    private boolean checkBoxPasseio;
    private boolean checkBoxTosa;

    // Construtor
    public Relatorio(int id, String cpfUsuario, String dataCheckIn, String dataCheckOut,
                     String dataRealizacaoReserva, String pet, double valorPago,
                     boolean checkBoxAlimentacaoEspecial, boolean checkBoxBanho,
                     boolean checkBoxPasseio, boolean checkBoxTosa) {
        this.id = id;
        this.cpfUsuario = cpfUsuario;
        this.dataCheckIn = dataCheckIn;
        this.dataCheckOut = dataCheckOut;
        this.dataRealizacaoReserva = dataRealizacaoReserva;
        this.pet = pet;
        this.valorPago = valorPago;
        this.checkBoxAlimentacaoEspecial = checkBoxAlimentacaoEspecial;
        this.checkBoxBanho = checkBoxBanho;
        this.checkBoxPasseio = checkBoxPasseio;
        this.checkBoxTosa = checkBoxTosa;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public void setCpfUsuario(String cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
    }

    public String getDataCheckIn() {
        return dataCheckIn;
    }

    public void setDataCheckIn(String dataCheckIn) {
        this.dataCheckIn = dataCheckIn;
    }

    public String getDataCheckOut() {
        return dataCheckOut;
    }

    public void setDataCheckOut(String dataCheckOut) {
        this.dataCheckOut = dataCheckOut;
    }

    public String getDataRealizacaoReserva() {
        return dataRealizacaoReserva;
    }

    public void setDataRealizacaoReserva(String dataRealizacaoReserva) {
        this.dataRealizacaoReserva = dataRealizacaoReserva;
    }

    public String getPet() {
        return pet;
    }

    public void setPet(String pet) {
        this.pet = pet;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public boolean isCheckBoxAlimentacaoEspecial() {
        return checkBoxAlimentacaoEspecial;
    }

    public void setCheckBoxAlimentacaoEspecial(boolean checkBoxAlimentacaoEspecial) {
        this.checkBoxAlimentacaoEspecial = checkBoxAlimentacaoEspecial;
    }

    public boolean isCheckBoxBanho() {
        return checkBoxBanho;
    }

    public void setCheckBoxBanho(boolean checkBoxBanho) {
        this.checkBoxBanho = checkBoxBanho;
    }

    public boolean isCheckBoxPasseio() {
        return checkBoxPasseio;
    }

    public void setCheckBoxPasseio(boolean checkBoxPasseio) {
        this.checkBoxPasseio = checkBoxPasseio;
    }

    public boolean isCheckBoxTosa() {
        return checkBoxTosa;
    }

    public void setCheckBoxTosa(boolean checkBoxTosa) {
        this.checkBoxTosa = checkBoxTosa;
    }
}

