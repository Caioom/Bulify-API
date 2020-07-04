package com.bulefy.api.dtos;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.bulefy.api.models.Lembrete;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
	private Long id;
	
	@NotEmpty(message = "O nome não pode estar vazio e deve possuir mais de 3 caractéres")
	@Size(min = 3)
	private String nome;
	
	@NotEmpty(message = "O email não pode estar vazio")
	@Email
	private String email;
	
	@NotEmpty(message = "O usuário deve possuir uma senha")
	@Size(min = 6)
	private String senha;
	
	private List<Lembrete> lembretes;
}
