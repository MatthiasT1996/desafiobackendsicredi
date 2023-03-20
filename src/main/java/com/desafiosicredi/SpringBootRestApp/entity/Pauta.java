package com.desafiosicredi.SpringBootRestApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pauta")
public class Pauta {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "tempo_fechamento")
    private Integer tempoFechamento;

    @Column(name = "data_inicio_pauta")
    private LocalDateTime dataInicioPauta;

    @Column(name = "data_fim_pauta")
    private LocalDateTime dataFimPauta;

    @Column(name = "fechada", nullable = false)
    private Boolean fechada;

    @JsonIgnore
    @OneToMany(mappedBy = "pauta")
    private List<VotacaoPauta> votacaoPautaList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getTempoFechamento() {
        return tempoFechamento;
    }

    public void setTempoFechamento(Integer tempoFechamento) {
        this.tempoFechamento = tempoFechamento;
    }

    public LocalDateTime getDataInicioPauta() {
        return dataInicioPauta;
    }

    public void setDataInicioPauta(LocalDateTime dataInicioPauta) {
        this.dataInicioPauta = dataInicioPauta;
    }

    public LocalDateTime getDataFimPauta() {
        return dataFimPauta;
    }

    public void setDataFimPauta(LocalDateTime dataFimPauta) {
        this.dataFimPauta = dataFimPauta;
    }

    public Boolean getFechada() {
        return fechada;
    }

    public void setFechada(Boolean fechada) {
        this.fechada = fechada;
    }

    public List<VotacaoPauta> getVotacaoPautaList() {
        return votacaoPautaList;
    }

    public void setVotacaoPautaList(List<VotacaoPauta> votacaoPautaList) {
        this.votacaoPautaList = votacaoPautaList;
    }
}
