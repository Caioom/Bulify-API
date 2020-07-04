package com.bulefy.api.dtos;

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
	private String nome;
	private String bula;
}
