package com.desafiosicredi.SpringBootRestApp.Response;

import java.util.Date;

public class ErrorMessage {
    private Date dataAtual;
    private String mensagem;

    public ErrorMessage() {
    }

    public ErrorMessage(Date dataAtual, String mensagem) {
        this.dataAtual = dataAtual;
        this.mensagem = mensagem;
    }

    public Date getDataAtual() {
        return dataAtual;
    }

    public void setDataAtual(Date dataAtual) {
        this.dataAtual = dataAtual;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
