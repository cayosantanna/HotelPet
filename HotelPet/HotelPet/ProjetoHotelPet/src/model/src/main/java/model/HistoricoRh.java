package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "historico_rh")
public class HistoricoRh implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "CPF não pode estar vazio.")
    @Size(min = 11, max = 14, message = "CPF deve conter entre 11 e 14 caracteres.")
    @Pattern(regexp = "\\d{11}|\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF inválido.")
    @Column(nullable = false, unique = true, length = 14)
    private String cpfRh;

    @NotNull(message = "Ação não pode estar vazia.")
    @Column(name = "acao", nullable = false, columnDefinition = "TEXT")
    private String acao;

    @Column(name = "data_hora", updatable = false, nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime dataHora;

    // Método que preenche automaticamente o campo dataHora ao criar o registro
    @PrePersist
    protected void onCreate() {
        this.dataHora = LocalDateTime.now();
    }

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
