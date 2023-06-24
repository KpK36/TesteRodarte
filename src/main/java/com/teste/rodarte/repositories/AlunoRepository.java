package com.teste.rodarte.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.rodarte.entities.AlunoEntity;

public interface AlunoRepository extends JpaRepository<AlunoEntity, Long>{

}
