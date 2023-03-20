package com.desafiosicredi.SpringBootRestApp.Verificador;

import com.desafiosicredi.SpringBootRestApp.entity.Log;
import com.desafiosicredi.SpringBootRestApp.entity.Pauta;
import com.desafiosicredi.SpringBootRestApp.repository.LogRepository;
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
    private final long MEIO_MINUTO = SEGUNDO * 30;
    private final long MINUTO = SEGUNDO * 60;
    private final long HORA = MINUTO * 60;

    @Autowired
    PautaRepository pautaRepository;

    @Autowired
    LogRepository logRepository;

    @Autowired
    private QueueSender queueSender;

    @Scheduled(fixedDelay = MINUTO)
    public void verificaPorMinuto() {
        List<Pauta> listaPauta = (List<Pauta>) pautaRepository.findAll();
        LocalDateTime dataAtual = LocalDateTime.now();
        Log log = null;
        String mensagem = "";
        for (Pauta pauta : listaPauta) {
            if(!pauta.getFechada() && pauta.getDataFimPauta().isBefore(dataAtual)){
                pauta.setFechada(true);
                pautaRepository.save(pauta);

                mensagem = "Pauta " + pauta.getNome() + " fechada às " + dataAtual;

                log = new Log(mensagem, dataAtual);
                logRepository.save(log);

                queueSender.send(mensagem);
            }
        }

    }
}
