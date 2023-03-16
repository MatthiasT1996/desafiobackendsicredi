package com.desafiosicredi.SpringBootRestApp.repository;

import com.desafiosicredi.SpringBootRestApp.entity.Associado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssociadoRepository extends CrudRepository<Associado, Integer> {
    public Boolean existsByCpf(String cpf);
    public Boolean existsByNome(String nome);
    public Optional<Associado> findByNome(String nome);
}
