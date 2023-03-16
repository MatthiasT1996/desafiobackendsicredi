package com.desafiosicredi.SpringBootRestApp.controller;

import com.desafiosicredi.SpringBootRestApp.entity.Pauta;
import com.desafiosicredi.SpringBootRestApp.repository.PautaRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/pauta")
public class PautaController {

    @Autowired
    PautaRepository cargoRepository;

    @ApiOperation(value = "Método que busca o Cargo pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<Pauta> listarById(@PathVariable Integer id) {
        Optional<Pauta> optional = cargoRepository.findById(id);
        if (optional.isPresent()) {
            Pauta cargo = cargoRepository.findById(id).get();
            return new ResponseEntity<>(cargo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Método que busca o Cargo pelo nome")
    @GetMapping("/pesquisar/{nome}")
    public ResponseEntity<Pauta> findByNome(@PathVariable String nome) {
        Optional<Pauta> optional = cargoRepository.findByNome(nome);
        if (optional.isPresent()) {
            Pauta cargo = cargoRepository.findByNome(nome).get();
            return new ResponseEntity<>(cargo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Método que lsita todos Cargo cadastrados")
    @GetMapping
    public List<Pauta> listar() {
        return (List<Pauta>) cargoRepository.findAll();
    }

    @ApiOperation(value = "Método que faz a insclusão do Cargo")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Pauta> inserir(@RequestBody Pauta cargo) {
        cargoRepository.save(cargo);

        return new ResponseEntity<>(cargo, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Método que faz a alteração do Cargo por ID")
    @PutMapping("/alterarPorId/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Pauta> atualizarPorId(@RequestBody Pauta cargo, @PathVariable Integer id) {
        Optional<Pauta> cargoOptional = cargoRepository.findById(id);
        if (cargoOptional.isPresent()) {
            Pauta cargoAtualizado = cargoOptional.get();
            cargoAtualizado.setNome(cargo.getNome());
            cargoRepository.save(cargoAtualizado);
            return new ResponseEntity<>(cargo, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    @ApiOperation(value = "Método que faz a alteração do Cargo por nome")
    @PutMapping("/alterarPorNome/{nome}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Pauta> atualizarPorNome(@RequestBody Pauta cargo, @PathVariable String nome) {
        Optional<Pauta> cargoOptional = cargoRepository.findByNome(nome);
        if (cargoOptional.isPresent()) {
            Pauta cargoAtualizado = cargoOptional.get();
            cargoAtualizado.setNome(cargo.getNome());
            cargoRepository.save(cargoAtualizado);
            return new ResponseEntity<>(cargo, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    @ApiOperation(value = "Método que exclui o Cargo pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Pauta> remover(@PathVariable Integer id) {
        cargoRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
