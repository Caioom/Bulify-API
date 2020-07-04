package com.bulefy.api.dtos;

import java.util.List;
import java.util.stream.Collectors;

import com.bulefy.api.models.Data;
import com.bulefy.api.models.Lembrete;
import com.bulefy.api.models.Remedio;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class LembreteDTO {
	public LembreteDTO() {}
	
	public LembreteDTO(Lembrete lembrete) {
		this.dataHora = this.converteParaString(lembrete.getDataHora());
		this.remedio = this.setRemedioDto(lembrete.getRemedio());
	}
	
	//@NotNull(message = "A data não pode ser vazia")
	private List<String> dataHora;
	
	private List<RemedioDTO> remedio;
	
	private List<String> converteParaString(List<Data> datas) {
		List<String> datasString = datas.stream()
											.map(d -> d.getDataHora().toString())
											.collect(Collectors.toList());
		return datasString;
	}
	
	private List<RemedioDTO> setRemedioDto(List<Remedio> remedios) {
		List<RemedioDTO> remediosDto = remedios.stream().map(RemedioDTO::new).collect(Collectors.toList());
		return remediosDto;
	}
	
}
