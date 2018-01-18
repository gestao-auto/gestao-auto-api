package br.edu.pucpr.gestaoauto.model.job;

import javax.batch.runtime.BatchStatus;
import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "job", catalog = "gestao_auto")
public class Job implements java.io.Serializable {
    private Integer codigo;
    private BatchStatus status;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private String mensagem;

    public Job() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "cod_job", unique = true, nullable = false)
    public Integer getCodigo() {
        return this.codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    @Column(name = "status", nullable = false)
    public BatchStatus getStatus() {
        return status;
    }

    public void setStatus(BatchStatus status) {
        this.status = status;
    }

    @Column(name = "data_inicio", nullable = false)
    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    @Column(name = "data_fim")
    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    @Column(name = "mensagem", length = 400)
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}