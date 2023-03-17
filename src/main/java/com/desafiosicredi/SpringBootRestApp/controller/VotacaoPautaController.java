package com.desafiosicredi.SpringBootRestApp.controller;

import com.desafiosicredi.SpringBootRestApp.dto.VotacaoPautaDTO;
import com.desafiosicredi.SpringBootRestApp.entity.Associado;
import com.desafiosicredi.SpringBootRestApp.entity.Pauta;
import com.desafiosicredi.SpringBootRestApp.entity.VotacaoPauta;
import com.desafiosicredi.SpringBootRestApp.repository.AssociadoRepository;
import com.desafiosicredi.SpringBootRestApp.repository.PautaRepository;
import com.desafiosicredi.SpringBootRestApp.repository.VotacaoPautaRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/votacaopauta")
public class VotacaoPautaController {

    @Autowired
    VotacaoPautaRepository votacaoPautaRepository;

    @Autowired
    PautaRepository pautaRepository;

    @Autowired
    AssociadoRepository associadoRepository;

    @ApiOperation(value = "Método que busca a Votação da Pauta pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<VotacaoPauta> listarById(@PathVariable Integer id) {
        Optional<VotacaoPauta> optional = votacaoPautaRepository.findById(id);
        if (optional.isPresent()) {
            VotacaoPauta votacaoPauta = votacaoPautaRepository.findById(id).get();
            return new ResponseEntity<>(votacaoPauta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Método que lista todas Votações Pautas cadastradas")
    @GetMapping
    public List<VotacaoPauta> listar() {
        return (List<VotacaoPauta>) votacaoPautaRepository.findAll();
    }

    @ApiOperation(value = "Método que lista todos votos para determinada pauta")
    @GetMapping("/listarVotacaoPauta/{idPauta}")
    public VotacaoPautaDTO listarByIdPauta(@PathVariable Integer idPauta) {
        List<VotacaoPauta> listaVotacaoPauta = votacaoPautaRepository.listarByIdPauta(idPauta);
        VotacaoPautaDTO votacaoPautaDTO = new VotacaoPautaDTO();

        for (VotacaoPauta votacaoPauta :
                listaVotacaoPauta) {
            votacaoPautaDTO.setIdPauta(votacaoPauta.getPauta().getId());
            if(votacaoPauta.getAprovada()){
                votacaoPautaDTO.setVotosSim(votacaoPautaDTO.getVotosSim() + 1);
            }else{
                votacaoPautaDTO.setVotosNao(votacaoPautaDTO.getVotosNao() + 1);
            }
        }
        return votacaoPautaDTO;
    }

    @ApiOperation(value = "Método que lista todas Votações de Pautas cadastradas com seus resultados")
    @GetMapping("/listartodaspautas")
    public List<VotacaoPautaDTO> listarTodasPautas() {
        List<VotacaoPautaDTO> listaVotacaoPautaDTO = new ArrayList<>();
        List<VotacaoPauta> listaVotacaoPauta = (List<VotacaoPauta>) votacaoPautaRepository.findAll();
        VotacaoPautaDTO votacaoPautaDTO = new VotacaoPautaDTO();
        for (VotacaoPauta votacaoPauta :
                listaVotacaoPauta) {
            Integer idPauta = votacaoPauta.getPauta().getId();
            if(idPauta == votacaoPautaDTO.getIdPauta()){
                if (votacaoPauta.getAprovada()) {
                    votacaoPautaDTO.setVotosSim(votacaoPautaDTO.getVotosSim() + 1);
                } else {
                    votacaoPautaDTO.setVotosNao(votacaoPautaDTO.getVotosNao() + 1);
                }
            } else {
                votacaoPautaDTO = new VotacaoPautaDTO();
                votacaoPautaDTO.setIdPauta(idPauta);
                if (votacaoPauta.getAprovada()) {
                    votacaoPautaDTO.setVotosSim(votacaoPautaDTO.getVotosSim() + 1);
                } else {
                    votacaoPautaDTO.setVotosNao(votacaoPautaDTO.getVotosNao() + 1);
                }
                listaVotacaoPautaDTO.add(votacaoPautaDTO);
            }
        }
        return listaVotacaoPautaDTO;
    }

    @ApiOperation(value = "Método que faz a inclusão da Votação da Pauta")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> inserir(@RequestBody VotacaoPauta votacaoPauta) {
        List<VotacaoPauta> listaVotacaoPauta = votacaoPautaRepository.listarByIdPautaAndIdAssociado(votacaoPauta.getPauta().getId(), votacaoPauta.getAssociado().getId());
        if (listaVotacaoPauta.isEmpty()) {
            votacaoPautaRepository.save(votacaoPauta);
            return new ResponseEntity<>("Voto computado com sucesso!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Impossível computar o voto, Associado já votou nessa pauta.", HttpStatus.CONFLICT);
        }
    }


    @ApiOperation(value = "Método que exclui a Votação da Pauta pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> remover(@PathVariable Integer id) {
        Optional<VotacaoPauta> VotacaoPautaOptional = votacaoPautaRepository.findById(id);
        if (VotacaoPautaOptional.isPresent()) {
            votacaoPautaRepository.deleteById(id);
            return new ResponseEntity<>("Voto excluído com sucesso!", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Voto não encontrada!", HttpStatus.NOT_FOUND);
        }
    }

}
