package com.teste.rodarte;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.teste.rodarte.repositories.AlunoRepository;
import com.teste.rodarte.service.AlunoService;

@SpringBootApplication
public class RodarteApplication {

	public static void main(String[] args) {
		SpringApplication.run(RodarteApplication.class, args);

	}

	@Autowired
	public void configureService(AlunoRepository alunoRepository) {
		AlunoService alunoService = new AlunoService(alunoRepository);
		alunoService.ImportaPlanilha();
	}

}
