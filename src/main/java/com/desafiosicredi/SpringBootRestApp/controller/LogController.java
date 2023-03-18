package com.desafiosicredi.SpringBootRestApp.controller;

import com.desafiosicredi.SpringBootRestApp.entity.Associado;
import com.desafiosicredi.SpringBootRestApp.entity.Log;
import com.desafiosicredi.SpringBootRestApp.repository.AssociadoRepository;
import com.desafiosicredi.SpringBootRestApp.repository.LogRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/logs")
public class LogController {

    @Autowired
    LogRepository logRepository;


    @ApiOperation(value = "Método que lsita todos Logs cadastrados")
    @GetMapping
    public List<Log> listar() {
        return (List<Log>) logRepository.findAll();
    }

}
