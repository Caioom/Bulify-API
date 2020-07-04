package com.bulefy.api.dtos;

import java.time.LocalDateTime;

import com.bulefy.api.models.Lembrete;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class LembreteDTO {
	public LembreteDTO() {}
	
	public LembreteDTO(Lembrete lembrete) {
		this.dataHora = lembrete.getDataHora();
		this.remedio = new RemedioDTO(lembrete.getRemedio());
	}
	
	//@NotNull(message = "A data não pode ser vazia")
	private LocalDateTime dataHora;
	
	private RemedioDTO remedio;
}
