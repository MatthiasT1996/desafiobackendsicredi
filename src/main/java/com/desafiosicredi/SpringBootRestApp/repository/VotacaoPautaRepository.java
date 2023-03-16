package com.desafiosicredi.SpringBootRestApp.repository;

import com.desafiosicredi.SpringBootRestApp.entity.VotacaoPauta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotacaoPautaRepository extends CrudRepository<VotacaoPauta, Integer> {
    @Query("SELECT vt from VotacaoPauta vt where vt.pauta.id = ?1")
    public List<VotacaoPauta> listarByIdPauta(Integer idPauta);
}
