package com.desafiosicredi.SpringBootRestApp.controller;

import com.desafiosicredi.SpringBootRestApp.entity.Associado;
import com.desafiosicredi.SpringBootRestApp.repository.AssociadoRepository;
import com.desafiosicredi.SpringBootRestApp.senders.QueueSender;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.InputMismatchException;
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
    public ResponseEntity<String> inserir(@RequestBody Associado associado) {
        if(!isCPFValido(associado.getCpf())){
            return new ResponseEntity<>("CPF inválido!", HttpStatus.NOT_ACCEPTABLE);
        }
        if (!associadoRepository.existsByCpf(associado.getCpf())) {
            associadoRepository.save(associado);
            return new ResponseEntity<>("Associado adicionado com sucesso!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Associado já cadastrado!", HttpStatus.CONFLICT);
        }
    }

    @ApiOperation(value = "Método que faz a alteração do Associado por ID")
    @PutMapping("/alterarPorId/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> atualizarPorId(@RequestBody Associado associado, @PathVariable Integer id) {
        Optional<Associado> associadoOptional = associadoRepository.findById(id);
        if (associadoOptional.isPresent()) {
            Associado associadoAtualizado = associadoOptional.get();
            associadoAtualizado.setNome(associado.getNome());
            associadoRepository.save(associadoAtualizado);
            return new ResponseEntity<>("Associado alterado com sucesso!", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Associado não encontrado!", HttpStatus.NOT_FOUND);
        }

    }

    @ApiOperation(value = "Método que exclui o Associado pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> remover(@PathVariable Integer id) {

        Optional<Associado> associadoOptional = associadoRepository.findById(id);
        if (associadoOptional.isPresent()) {
            associadoRepository.deleteById(id);
            return new ResponseEntity<>("Associado excluído com sucesso!", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Associado não encontrado!", HttpStatus.NOT_FOUND);
        }
    }

    public boolean isCPFValido(String CPF) {

        CPF = removeCaracteresEspeciais(CPF);

        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222") || CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555") || CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888") || CPF.equals("99999999999") || (CPF.length() != 11))
            return (false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else
                dig10 = (char) (r + 48); // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else
                dig11 = (char) (r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                return (true);
            else
                return (false);
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    private String removeCaracteresEspeciais(String doc) {
        if (doc.contains(".")) {
            doc = doc.replace(".", "");
        }
        if (doc.contains("-")) {
            doc = doc.replace("-", "");
        }
        if (doc.contains("/")) {
            doc = doc.replace("/", "");
        }
        return doc;
    }

}
