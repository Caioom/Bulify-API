package com.bulefy.api.models;

import static java.lang.Integer.valueOf;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.bulefy.api.dtos.LembreteDTO;
import com.bulefy.api.dtos.RemedioDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "lembrete")
@Entity
public class Lembrete {
	public Lembrete() {}
	
	public Lembrete(LembreteDTO dto) {
		this.dataHora = this.converteData(dto.getDataHora());
		this.remedio = this.setRemedios(dto.getRemedios());
	}
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Data> dataHora;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Remedio> remedio;
	
	//dd/MM/yyyy-HH:mm
	@SuppressWarnings("deprecation")
	private List<Data> converteData(List<String> data) {
		List<Data> datas = new ArrayList<>();
		
		for (String d : data) {
			String[] dataSplit = d.split("-");
			
			String[] dataCalendario = dataSplit[0].split("/");
			String[] horas = dataSplit[1].split(":");
			
			int ano = valueOf(dataCalendario[2]);
			int mes = valueOf(dataCalendario[1]) - 1;
			int dia = valueOf(dataCalendario[0]);
			
			int hora = valueOf(horas[0]);
			int minuto = valueOf(horas[1]); 
			
			Date date = new Date(ano - 1900, mes, dia, hora, minuto);
			datas.add(new Data(date));
		}
		
		return datas;
	}
	
	private List<Remedio> setRemedios(List<RemedioDTO> remediosDto) {
		List<Remedio> remedios = remediosDto.stream().map(Remedio::new).collect(Collectors.toList());
		return remedios;
	}
}
