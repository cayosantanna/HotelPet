package model;

public class Pet {
    private int id;
    private String nome;
    private String datanascimento;
    private String especie;
    private String raca;
    private String porte;
    private String sexo;
    private String caracteristicasFisicas;
    private String historicoDoencas;
    private String medicacoes;
    private String cpfResponsavel;

    // Construtor com parâmetros
    public Pet(int id, String nome, String datanascimento, String especie, String raca, String porte, 
               String sexo, String caracteristicasFisicas, String historicoDoencas, 
               String medicacoes, String cpfResponsavel) {
        this.id = id;
        this.nome = nome;
        this.datanascimento = datanascimento;
        this.especie = especie;
        this.raca = raca;
        this.porte = porte;
        this.sexo = sexo;
        this.caracteristicasFisicas = caracteristicasFisicas;
        this.historicoDoencas = historicoDoencas;
        this.medicacoes = medicacoes;
        this.cpfResponsavel = cpfResponsavel;
    }

    // Construtor padrão (sem parâmetros) com valores padrão
    public Pet() {
        this.id = 0;
        this.nome = "";
        this.datanascimento = "";
        this.especie = "";
        this.raca = "";
        this.porte = "";
        this.sexo = "";
        this.caracteristicasFisicas = "";
        this.historicoDoencas = "";
        this.medicacoes = "";
        this.cpfResponsavel = "";
    }

    // Métodos getter e setter para acessar e modificar os atributos

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDatanascimento() {
        return datanascimento;
    }

    public void setDatanascimento(String datanascimento) {
        this.datanascimento = datanascimento;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCaracteristicasFisicas() {
        return caracteristicasFisicas;
    }

    public void setCaracteristicasFisicas(String caracteristicasFisicas) {
        this.caracteristicasFisicas = caracteristicasFisicas;
    }

    public String getHistoricoDoencas() {
        return historicoDoencas;
    }

    public void setHistoricoDoencas(String historicoDoencas) {
        this.historicoDoencas = historicoDoencas;
    }

    public String getMedicacoes() {
        return medicacoes;
    }

    public void setMedicacoes(String medicacoes) {
        this.medicacoes = medicacoes;
    }

    public String getCpfResponsavel() {
        return cpfResponsavel;
    }

    public void setCpfResponsavel(String cpfResponsavel) {
        this.cpfResponsavel = cpfResponsavel;
    }

}
