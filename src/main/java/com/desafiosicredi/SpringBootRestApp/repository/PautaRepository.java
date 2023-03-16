package com.desafiosicredi.SpringBootRestApp.repository;

import com.desafiosicredi.SpringBootRestApp.entity.Pauta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PautaRepository extends CrudRepository<Pauta, Integer> {
    public Boolean existsByNome(String nome);
    public Optional<Pauta> findByNome(String nome);
}
