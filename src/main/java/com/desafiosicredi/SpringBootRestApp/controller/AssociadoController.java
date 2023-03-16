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
    AssociadoRepository associadoRepository;

    @ApiOperation(value = "Método que busca o Associado pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<Associado> listarById(@PathVariable Integer id) {
        Optional<Associado> optional = associadoRepository.findById(id);
        if (optional.isPresent()) {
            Associado associado = associadoRepository.findById(id).get();
            return new ResponseEntity<>(associado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Método que busca o Associado pelo nome")
    @GetMapping("/pesquisar/{nome}")
    public ResponseEntity<Associado> findByNome(@PathVariable String nome) {
        Optional<Associado> optional = associadoRepository.findByNome(nome);
        if (optional.isPresent()) {
            Associado associado = associadoRepository.findByNome(nome).get();
            return new ResponseEntity<>(associado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Método que lsita todos Associados cadastrados")
    @GetMapping
    public List<Associado> listar() {
        return (List<Associado>) associadoRepository.findAll();
    }

    @ApiOperation(value = "Método que faz a inclusão do Associado")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Associado> inserir(@RequestBody Associado associado) {
        if (!associadoRepository.existsByCpf(associado.getCpf())) {
            associadoRepository.save(associado);
            return new ResponseEntity<>(associado, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    @ApiOperation(value = "Método que faz a alteração do Associado por ID")
    @PutMapping("/alterarPorId/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Associado> atualizarPorId(@RequestBody Associado associado, @PathVariable Integer id) {
        Optional<Associado> associadoOptional = associadoRepository.findById(id);
        if (associadoOptional.isPresent()) {
            Associado associadoAtualizado = associadoOptional.get();
            associadoAtualizado.setNome(associado.getNome());
            associadoRepository.save(associadoAtualizado);
            return new ResponseEntity<>(associado, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    @ApiOperation(value = "Método que faz a alteração do Associado por nome")
    @PutMapping("/alterarPorNome/{nome}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Associado> atualizarPorNome(@RequestBody Associado associado, @PathVariable String nome) {
        Optional<Associado> associadoOptional = associadoRepository.findByNome(nome);
        if (associadoOptional.isPresent()) {
            Associado associadoAtualizado = associadoOptional.get();
            associadoAtualizado.setNome(associado.getNome());
            associadoRepository.save(associadoAtualizado);
            return new ResponseEntity<>(associado, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    @ApiOperation(value = "Método que exclui o Associado pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Associado> remover(@PathVariable Integer id) {
        associadoRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
