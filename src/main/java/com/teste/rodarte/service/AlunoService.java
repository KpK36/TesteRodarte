package com.teste.rodarte.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.teste.rodarte.entities.AlunoEntity;
import com.teste.rodarte.repositories.AlunoRepository;

@Service
public class AlunoService {

	AlunoRepository alunoRepository;

	public AlunoService(AlunoRepository alunoRepository) {
		this.alunoRepository = alunoRepository;
	}

	public void ImportaPlanilha() {

		Set<AlunoEntity> alunos = new HashSet<>();

		try {
			// endereço do arquivo
			String path = "C:\\Users\\kayop\\Documents\\TesteRodarte\\BaseImportacao.xlsx";

			FileInputStream fis = new FileInputStream(new File(path));

			// arquivo que representa o excel
			Workbook workbook = new XSSFWorkbook(fis);

			// pega a primeira planilha do excel
			Sheet sheet = workbook.getSheetAt(0);

			// Linhas
			for (Row row : sheet) {

				AlunoEntity aluno = new AlunoEntity();

				// celulas
				for (Cell cell : row) {

					System.out.println("-----------------------------------------------------");
					System.out.println("Endereço: " + cell.getAddress());

					if (cell.getRowIndex() == 0) {
						System.out.println("Valor: " + cell.getStringCellValue());

					}

					else {

						switch (cell.getColumnIndex()) {
						case 0:

							System.out.println("Valor: " + (long) cell.getNumericCellValue());
							aluno.setId((long) cell.getNumericCellValue());
							break;

						case 1:

//							if (cell.getStringCellValue() != null) {
//								System.out.println("Valor: " + cell.getStringCellValue());
//							}
							aluno.setName(cell.getStringCellValue());
							break;
						case 2:
//							if (cell.getStringCellValue() != null) {
//								System.out.println("Valor: " + cell.getStringCellValue());
//							}
							aluno.setSexo(cell.getStringCellValue());
							break;

						case 3:
							SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");
							String dataFormatada = dataFormato.format(cell.getDateCellValue());

							DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/uuuu", Locale.US);
							LocalDate ld = LocalDate.parse(dataFormatada, formatar);

							//LocalDate dt1 = LocalDate.parse(dataFormatada, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

							//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

							//System.out.println("Data Teste: " + dt1.format(formatter));

							System.out.println("Valor: " + dataFormatada);
							aluno.setDataNascimento(ld);

							

							// calculo idade
							LocalDate dataAtual = LocalDate.now();
							Period periodo = Period.between(ld, dataAtual);
							int idade = periodo.getYears();

							System.out.println("Idade: " + idade);
							System.out.println(dataAtual);
							aluno.setIdade(idade);
							break;

						case 4:

//							if (cell.getNumericCellValue() != 0) {
//								System.out.println("Valor: " + (int) cell.getNumericCellValue());
//							}
							aluno.setNota1((int) cell.getNumericCellValue());
							break;
						case 5:

//							if (cell.getNumericCellValue() != 0) {
//								System.out.println("Valor: " + (int) cell.getNumericCellValue());
//							}
							aluno.setNota2((int) cell.getNumericCellValue());
							break;
						case 6:

//							if (cell.getNumericCellValue() != 0) {
//								System.out.println("Valor: " + (int) cell.getNumericCellValue());
//							}
							aluno.setNota3((int) cell.getNumericCellValue());
							break;

						default:
							break;
						}

					}

					//System.out.println(aluno);
				}

				if (aluno.getId() != 0 && aluno.getName() != null && aluno.getSexo() != null
						&& aluno.getDataNascimento() != null && aluno.getNota1() != 0 && aluno.getNota2() != 0
						&& aluno.getNota3() != 0) {

					alunos.add(aluno);

				}

			}

			// Fecha o arquivo e libera os recursos
			workbook.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		LinkedHashSet<AlunoEntity> semAlunoRepetido = new LinkedHashSet<AlunoEntity>(alunos);
		List<AlunoEntity> alunosOrdenadosIdade = new ArrayList<AlunoEntity>(alunos);

		Collections.sort(alunosOrdenadosIdade, Comparator.comparing(AlunoEntity::getIdade));

//		System.out.println("===============================================");		
//
//		System.out.println("SEM ALUNO REPETIDO\n");
//
//		for (AlunoEntity aluno : semAlunoRepetido) {
//
//			System.out.println(aluno);
//		}

//		System.out.println("===============================================");
//
//		System.out.println("ORDENADOS POR IDADE\n");

		for (AlunoEntity aluno : alunosOrdenadosIdade) {
			
			int media = (aluno.getNota1() + aluno.getNota2() + aluno.getNota3()) / 3;
			aluno.setMedia(media);
			
			System.out.println(aluno);
		}

		alunoRepository.saveAll(alunosOrdenadosIdade);

	}

	

}