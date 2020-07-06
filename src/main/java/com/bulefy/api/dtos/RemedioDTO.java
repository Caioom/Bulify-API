package com.bulefy.api.dtos;

import javax.validation.constraints.NotEmpty;

import com.bulefy.api.models.Remedio;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RemedioDTO {
	public RemedioDTO() {}
	
	public RemedioDTO(Remedio remedio) {
		this.id = remedio.getId();
		this.nome = remedio.getNome();
		this.bula = remedio.getBula();
	}
	
	private Long id;
	
	@NotEmpty(message = "O nome do remédio não deve estar vazio")
	private String nome;
	private String bula;
}
