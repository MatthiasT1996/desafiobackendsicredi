package com.desafiosicredi.SpringBootRestApp.entity;

import javax.persistence.*;

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
}
