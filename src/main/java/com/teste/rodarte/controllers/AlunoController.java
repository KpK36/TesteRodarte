package com.teste.rodarte.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.rodarte.entities.AlunoEntity;
import com.teste.rodarte.repositories.AlunoRepository;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {
	
	private final AlunoRepository alunoRepository;

    public AlunoController(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }
    
    @GetMapping
    public List<AlunoEntity> getAlunos(){
    	
    	return alunoRepository.findAll();
    	
    }


    
   

}
