package com.desafiosicredi.SpringBootRestApp.Verificador;

import com.desafiosicredi.SpringBootRestApp.entity.Pauta;
import com.desafiosicredi.SpringBootRestApp.repository.PautaRepository;
import com.desafiosicredi.SpringBootRestApp.senders.QueueSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@EnableScheduling
public class VerificaFechamentoPauta {
    private final long SEGUNDO = 1000;
    private final long MINUTO = SEGUNDO * 60;
    private final long HORA = MINUTO * 60;

    @Autowired
    PautaRepository pautaRepository;

    @Autowired
    private QueueSender queueSender;

    @Scheduled(fixedDelay = MINUTO)
    public void verificaPorMinuto() {
        List<Pauta> listaPauta = (List<Pauta>) pautaRepository.findAll();
        LocalDateTime dataAtual = LocalDateTime.now();
        for (Pauta pauta : listaPauta) {
            if(!pauta.getFechada() && pauta.getDataFimPauta().isBefore(dataAtual)){
                pauta.setFechada(true);
                pautaRepository.save(pauta);
                queueSender.send("Pauta " + pauta.getNome() + " fechada às " + LocalDateTime.now());
            }
        }

    }
}
