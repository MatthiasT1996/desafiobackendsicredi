package com.desafiosicredi.SpringBootRestApp;

import com.desafiosicredi.SpringBootRestApp.repository.AssociadoRepository;
import com.desafiosicredi.SpringBootRestApp.repository.PautaRepository;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableRabbit
@SpringBootApplication
@EnableSwagger2
public class SpringBootRestAppApplication implements CommandLineRunner{

	@Autowired
	PautaRepository pautaRepository;

	@Autowired
	AssociadoRepository associadoRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestAppApplication.class, args);}

	@Override
	public void run(String... args) throws Exception {

	}
}

