package com.bulefy.api.models;

import java.time.LocalDateTime;

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
		this.dataHora = LocalDateTime.now();
		this.remedio = new Remedio(dto.getRemedio());
	}
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	
	@Column(name = "dia_hora_lembrete")
	private LocalDateTime dataHora;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	private Remedio remedio;
}
