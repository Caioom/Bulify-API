package com.bulefy.api.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Table(name = "date")
@Entity
@Getter
@Setter
public class Data {
	public Data() {}
	
	public Data(Date data) {
		this.dataHora = data;
	}
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	private Date dataHora;
}
