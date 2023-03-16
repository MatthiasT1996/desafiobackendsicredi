package com.desafiosicredi.SpringBootRestApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pauta")
public class Pauta {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "fechada", nullable = false)
    private Boolean fechada;

    @JsonIgnore
    @OneToMany(mappedBy = "pauta")
    private List<VotacaoPauta> votacaoPautaList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
