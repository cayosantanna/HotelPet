package model;

import javax.persistence.*;

@Entity
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
<<<<<<< HEAD
    
     public HistoricoReserva(String nomePet,String cpf) {
        this.nomePet = nomePet;
        this.cpf = cpf;
    }       
    
    public void copiar(HistoricoReserva outro){
        this.nomePet = outro.getNomePet();
        this.cpf = outro.getCpf();
    }
    
     @Override
    public String toString() {
        String txt = "---- Dados do Cliente e Pet ------\n"
        + super.toString()
        +" NomePet: "+ this.nomePet+ "\n"
        +" CPF: "+ this.cpf + "\n"
        +"-------------------------------------\n";    
        
        return txt;
    }     
    
     public String cabecalho(){
        return "Nome;CPF;\n";
    }
     
     public String atributoToCSV(){
        String aux = this.nomePet + ";" + 
                this.cpf +";"+"\n";
        return aux;    
    }
     
      /**
     * Preenche os campos do objeto com uma linha CSV
     * @param csv 
     */
    public void CSVToAtributo(String csv){
        String vetor[] = csv.split(";");
        
        this.nomePet = vetor[0];
        this.cpf = vetor[1];

    }
    
     public String getCpf() {
=======

    // Método para copiar dados de outro histórico de reserva
    public void copiar(HistoricoReserva outro) {
        this.nomePet = outro.getNomePet();
        this.cpf = outro.getCpf();
    }

    // Getters e Setters
    public String getCpf() {
>>>>>>> Main
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

<<<<<<< HEAD
=======
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
>>>>>>> Main
}
