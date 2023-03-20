package com.desafiosicredi.SpringBootRestApp.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "votacao_pauta")
public class VotacaoPauta {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "aprovada", nullable = false)
    private Boolean aprovada;

    @ManyToOne
    @JoinColumn(name = "id_pauta")
    private Pauta pauta;

    @ManyToOne
    @JoinColumn(name = "id_associado")
    private Associado associado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getAprovada() {
        return aprovada;
    }

    public void setAprovada(Boolean aprovada) {
        this.aprovada = aprovada;
    }

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }

    public Associado getAssociado() {
        return associado;
    }

    public void setAssociado(Associado associado) {
        this.associado = associado;
    }
}
