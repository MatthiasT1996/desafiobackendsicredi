package com.desafiosicredi.SpringBootRestApp.controller;

import com.desafiosicredi.SpringBootRestApp.entity.Associado;
import com.desafiosicredi.SpringBootRestApp.repository.AssociadoRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/associado")
public class AssociadoController {

    @Autowired
    AssociadoRepository trabalhadorRepository;

    @ApiOperation(value = "Método que busca o Trabalhador pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<Associado> listarById(@PathVariable Integer id) {
        Optional<Associado> optional = trabalhadorRepository.findById(id);
        if (optional.isPresent()) {
            Associado trabalhador = trabalhadorRepository.findById(id).get();
            return new ResponseEntity<>(trabalhador, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Método que busca o Trabalhador pelo nome")
    @GetMapping("/pesquisar/{nome}")
    public ResponseEntity<Associado> findByNome(@PathVariable String nome) {
        Optional<Associado> optional = trabalhadorRepository.findByNome(nome);
        if (optional.isPresent()) {
            Associado trabalhador = trabalhadorRepository.findByNome(nome).get();
            return new ResponseEntity<>(trabalhador, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Método que lsita todos Trabalhadores cadastrados")
    @GetMapping
    public List<Associado> listar() {
        return (List<Associado>) trabalhadorRepository.findAll();
    }

    @ApiOperation(value = "Método que faz a insclusão do Trabalhador")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Associado> inserir(@RequestBody Associado trabalhador) {
        if (!trabalhadorRepository.existsByCpf(trabalhador.getCpf())) {
            trabalhadorRepository.save(trabalhador);
            return new ResponseEntity<>(trabalhador, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    @ApiOperation(value = "Método que faz a alteração do Trabalhador por ID")
    @PutMapping("/alterarPorId/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Associado> atualizarPorId(@RequestBody Associado trabalhador, @PathVariable Integer id) {
        Optional<Associado> trabalhadorOptional = trabalhadorRepository.findById(id);
        if (trabalhadorOptional.isPresent()) {
            Associado trabalhadorAtualizado = trabalhadorOptional.get();
            trabalhadorAtualizado.setNome(trabalhador.getNome());
            trabalhadorRepository.save(trabalhadorAtualizado);
            return new ResponseEntity<>(trabalhador, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    @ApiOperation(value = "Método que faz a alteração do Trabalhador por nome")
    @PutMapping("/alterarPorNome/{nome}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Associado> atualizarPorNome(@RequestBody Associado trabalhador, @PathVariable String nome) {
        Optional<Associado> trabalhadorOptional = trabalhadorRepository.findByNome(nome);
        if (trabalhadorOptional.isPresent()) {
            Associado trabalhadorAtualizado = trabalhadorOptional.get();
            trabalhadorAtualizado.setNome(trabalhador.getNome());
            trabalhadorRepository.save(trabalhadorAtualizado);
            return new ResponseEntity<>(trabalhador, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    @ApiOperation(value = "Método que exclui o Trabalhador pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Associado> remover(@PathVariable Integer id) {
        trabalhadorRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
