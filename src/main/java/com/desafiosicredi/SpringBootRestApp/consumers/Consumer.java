package com.desafiosicredi.SpringBootRestApp.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload String mensagem){
        System.out.println(mensagem);
    }
}
