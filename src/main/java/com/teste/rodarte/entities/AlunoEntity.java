package com.teste.rodarte.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_aluno")

public class AlunoEntity {

	@Id
	private long id;

	@Column(length = 100, nullable = false)
	private String name;

	@Column(length = 1, nullable = false)
	@JsonIgnore
	private String sexo;

	@Column(length = 15, nullable = false)
	@JsonIgnore
	private LocalDate dataNascimento;

	@Column(length = 5, nullable = false)
	@JsonIgnore
	private int nota1;

	@Column(length = 5, nullable = false)
	@JsonIgnore
	private int nota2;

	@Column(length = 5, nullable = false)
	@JsonIgnore
	private int nota3;

	@Column(length = 5, nullable = false)
	private int media;

	@Column(length = 5, nullable = false)
	private int idade;

	public AlunoEntity() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public LocalDate getDataNascimento() {

		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
	
		this.dataNascimento = dataNascimento;

	}

	public int getNota1() {
		return nota1;
	}

	public void setNota1(int nota1) {
		this.nota1 = nota1;
	}

	public int getNota2() {
		return nota2;
	}

	public void setNota2(int nota2) {
		this.nota2 = nota2;
	}

	public int getNota3() {
		return nota3;
	}
	
	public void setNota3(int nota3) {
		this.nota3 = nota3;
	}

	public int getMedia() {
		return media;
	}

	public void setMedia(int media) {
		this.media = media;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	@Override
	public String toString() {
		return "AlunoEntity [id=" + id + ", name=" + name + ", sexo=" + sexo + ", dataNascimento=" + dataNascimento
				+ ", nota1=" + nota1 + ", nota2=" + nota2 + ", nota3=" + nota3 + ", media=" + media + ", idade=" + idade
				+ "]";
	}

}
