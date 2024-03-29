package com.desafiosicredi.SpringBootRestApp.controller;

import com.desafiosicredi.SpringBootRestApp.entity.Log;
import com.desafiosicredi.SpringBootRestApp.entity.Pauta;
import com.desafiosicredi.SpringBootRestApp.repository.LogRepository;
import com.desafiosicredi.SpringBootRestApp.repository.PautaRepository;
import com.desafiosicredi.SpringBootRestApp.senders.QueueSender;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/pauta")
public class PautaController {

    @Autowired
    PautaRepository pautaRepository;

    @Autowired
    LogRepository logRepository;

    @ApiOperation(value = "Método que busca a Pauta pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<Pauta> listarById(@PathVariable Integer id) {
        Optional<Pauta> optional = pautaRepository.findById(id);
        if (optional.isPresent()) {
            Pauta pauta = pautaRepository.findById(id).get();
            return new ResponseEntity<>(pauta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Método que busca a Pauta pelo nome")
    @GetMapping("/pesquisar/{nome}")
    public ResponseEntity<Pauta> findByNome(@PathVariable String nome) {
        Optional<Pauta> optional = pautaRepository.findByNome(nome);
        if (optional.isPresent()) {
            Pauta pauta = pautaRepository.findByNome(nome).get();
            return new ResponseEntity<>(pauta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Método que lista todas Pautas cadastradas")
    @GetMapping
    public List<Pauta> listar() {
        return (List<Pauta>) pautaRepository.findAll();
    }

    @ApiOperation(value = "Método que faz a inclusão da Pauta")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> inserir(@RequestBody Pauta pauta) {
        Integer tempoFechamentoPauta = pauta.getTempoFechamento();

        if(tempoFechamentoPauta == null){
            tempoFechamentoPauta = 60;
            pauta.setTempoFechamento(60);
        }

        LocalDateTime dataInicioPauta =  LocalDateTime.now();
        LocalDateTime dataFimPauta = dataInicioPauta.plusSeconds(tempoFechamentoPauta);
        pauta.setDataInicioPauta(dataInicioPauta);
        pauta.setDataFimPauta(dataFimPauta);

        pautaRepository.save(pauta);

        Log log = new Log("Pauta " + pauta.getNome() + " incluída com sucesso", LocalDateTime.now());
        logRepository.save(log);

        return new ResponseEntity<>("Pauta adicionada com sucesso!", HttpStatus.CREATED);
    }

    @ApiOperation(value = "Método que faz a alteração da Pauta por ID")
    @PutMapping("/alterarPorId/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> atualizarPorId(@RequestBody Pauta pauta, @PathVariable Integer id) {
        Optional<Pauta> pautaOptional = pautaRepository.findById(id);
        if (pautaOptional.isPresent()) {
            Pauta pautaAtualizado = pautaOptional.get();
            pautaAtualizado.setNome(pauta.getNome());
            pautaRepository.save(pautaAtualizado);
            Log log = new Log("Pauta " + pautaAtualizado.getNome() + " alterada com sucesso", LocalDateTime.now());
            logRepository.save(log);
            return new ResponseEntity<>("Pauta alterada com sucesso!", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Pauta não encontrada!", HttpStatus.NOT_FOUND);
        }

    }

    @ApiOperation(value = "Método que faz a alteração da Pauta por nome")
    @PutMapping("/alterarPorNome/{nome}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> atualizarPorNome(@RequestBody Pauta pauta, @PathVariable String nome) {
        Optional<Pauta> pautaOptional = pautaRepository.findByNome(nome);
        if (pautaOptional.isPresent()) {
            Pauta pautaAtualizado = pautaOptional.get();
            pautaAtualizado.setNome(pauta.getNome());
            pautaRepository.save(pautaAtualizado);
            Log log = new Log("Pauta " + pautaAtualizado.getNome() + " alterada com sucesso", LocalDateTime.now());
            logRepository.save(log);
            return new ResponseEntity<>("Pauta alterada com sucesso!", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Pauta não encontrada!", HttpStatus.NOT_FOUND);
        }

    }

    @ApiOperation(value = "Método que exclui a Pauta pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> remover(@PathVariable Integer id) {
        Optional<Pauta> pautaOptional = pautaRepository.findById(id);
        if (pautaOptional.isPresent()) {
            Log log = new Log("Pauta " + pautaOptional.get().getNome() + " excluída com sucesso", LocalDateTime.now());
            logRepository.save(log);
            pautaRepository.deleteById(id);
            return new ResponseEntity<>("Pauta excluída com sucesso!", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Pauta não encontrada!", HttpStatus.NOT_FOUND);
        }
    }

}
