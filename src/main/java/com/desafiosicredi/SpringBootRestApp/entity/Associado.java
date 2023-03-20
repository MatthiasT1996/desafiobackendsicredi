package com.desafiosicredi.SpringBootRestApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "associado")
public class Associado {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String nome;

    @JsonIgnore
    @OneToMany(mappedBy = "associado")
    private List<VotacaoPauta> votacaoPautaList;

    public Associado() {
    }

    public Associado(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<VotacaoPauta> getVotacaoPautaList() {
        return votacaoPautaList;
    }

    public void setVotacaoPautaList(List<VotacaoPauta> votacaoPautaList) {
        this.votacaoPautaList = votacaoPautaList;
    }
}
