package com.desafiosicredi.SpringBootRestApp.controller;

import com.desafiosicredi.SpringBootRestApp.entity.Associado;
import com.desafiosicredi.SpringBootRestApp.repository.AssociadoRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    AssociadoRepository associadoRepository;

    @ApiOperation(value = "Método que lista todos associados cadastrados")
    @GetMapping
    public List<Associado> listar() {
        return (List<Associado>) associadoRepository.findAll();
    }
}
