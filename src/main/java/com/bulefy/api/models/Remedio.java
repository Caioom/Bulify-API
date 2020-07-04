package com.bulefy.api.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.bulefy.api.dtos.RemedioDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "remedio")
@Entity
public class Remedio {
	public Remedio() {}
	
	public Remedio(RemedioDTO dto) {
		this.nome = dto.getNome();
	}
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	
	@Column(name = "nome_remedio")
	private String nome;
	
	@Transient
	private String bula;
}
