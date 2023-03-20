package com.desafiosicredi.SpringBootRestApp.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "logs")
public class Log {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String mensagem;

    @Column(name = "data_log")
    private LocalDateTime dataLog;

    public Log() {
    }

    public Log(String mensagem, LocalDateTime dataLog) {
        this.mensagem = mensagem;
        this.dataLog = dataLog;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDateTime getDataLog() {
        return dataLog;
    }

    public void setDataLog(LocalDateTime dataLog) {
        this.dataLog = dataLog;
    }
}
