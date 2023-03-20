package com.desafiosicredi.SpringBootRestApp.dto;

import com.desafiosicredi.SpringBootRestApp.entity.VotacaoPauta;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class VotacaoPautaDTO implements Serializable {

    private Integer idPauta;

    private Integer votosSim;

    private Integer votosNao;

    public VotacaoPautaDTO() {
        this.votosSim = 0;
        this.votosNao = 0;
    }

    public Integer getIdPauta() {
        return idPauta;
    }

    public void setIdPauta(Integer idPauta) {
        this.idPauta = idPauta;
    }

    public Integer getVotosSim() {
        return votosSim;
    }

    public void setVotosSim(Integer votosSim) {
        this.votosSim = votosSim;
    }

    public Integer getVotosNao() {
        return votosNao;
    }

    public void setVotosNao(Integer votosNao) {
        this.votosNao = votosNao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VotacaoPautaDTO that = (VotacaoPautaDTO) o;
        return idPauta == that.idPauta;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPauta);
    }
}
