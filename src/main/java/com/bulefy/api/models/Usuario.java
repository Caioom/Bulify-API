package com.bulefy.api.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.bulefy.api.dtos.UsuarioDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "usuario")
@Entity
public class Usuario {
	public Usuario() {}
	
	public Usuario(UsuarioDTO dto) {
		this.email = dto.getEmail();
		this.nome = dto.getNome();
		this.senha = dto.getSenha();
	}
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@ApiModelProperty(notes = "Id gerado no bd, não informar valor ao criar")
	private Long id;
	
	@Column(name = "nome_usuario")
	private String nome;
	
	@Column(name = "email_usuario")
	private String email;
	
	@Column(name = "senha_usuario")
	private String senha;
	
	@OneToMany
	private List<Lembrete> lembretes;
	
	public void addLembrete(Lembrete lembrete) {
		this.lembretes.add(lembrete);
	}
}
