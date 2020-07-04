package com.bulefy.api.dtos;

import com.bulefy.api.models.Lembrete;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class LembreteDTO {
	public LembreteDTO() {}
	
	public LembreteDTO(Lembrete lembrete) {
		this.dataHora = lembrete.getDataHora().toString();
		this.remedio = new RemedioDTO(lembrete.getRemedio());
	}
	
	//@NotNull(message = "A data não pode ser vazia")
	private String dataHora;
	
	private RemedioDTO remedio;
	
}
