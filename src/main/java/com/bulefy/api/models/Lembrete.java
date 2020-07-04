package com.bulefy.api.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.bulefy.api.dtos.LembreteDTO;

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
		this.remedio = new Remedio(dto.getRemedio());
	}
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	
	@Column(name = "dia_hora_lembrete")
	private Date dataHora;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	private Remedio remedio;
	
	//dd/MM/yyyy-HH:mm
	@SuppressWarnings("deprecation")
	private Date converteData(String data) {
		
		String[] dataSplit = data.split("-");
		
		String[] dataCalendario = dataSplit[0].split("/");
		String[] horas = dataSplit[1].split(":");
		
		int ano = Integer.valueOf(dataCalendario[2]);
		int mes = Integer.valueOf(dataCalendario[1]) - 1;
		int dia = Integer.valueOf(dataCalendario[0]);
		
		int hora = Integer.valueOf(horas[0]);
		int minuto = Integer.valueOf(horas[1]);
		
		Date date = new Date(ano, mes, dia, hora, minuto);
		
		return date;
	}
}
