package com.desafiosicredi.SpringBootRestApp.repository;

import com.desafiosicredi.SpringBootRestApp.entity.Pauta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PautaRepository extends CrudRepository<Pauta, Integer> {
    public Boolean existsByNome(String nome);
    public Optional<Pauta> findByNome(String nome);

    @Query("SELECT p from Pauta p where p.id = ?1 AND p.fechada = true")
    List<Pauta> isPautaFechada(long idPauta);
}
