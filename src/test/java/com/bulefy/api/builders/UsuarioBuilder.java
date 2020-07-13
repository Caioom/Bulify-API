package com.bulefy.api.builders;

import java.util.Arrays;

import com.bulefy.api.models.Usuario;

public class UsuarioBuilder {
	private Usuario usuario;
	
	private UsuarioBuilder() {}
	
	public static UsuarioBuilder umUsuario() {
		UsuarioBuilder builder = new UsuarioBuilder();
		builder.usuario = new Usuario();
		builder.usuario.setEmail("usuario@email.com");
		builder.usuario.setLembretes(Arrays.asList());
		builder.usuario.setNome("Usuario");
		builder.usuario.setSenha("usuario123");
		return builder;
	}
	
	public Usuario agora() {
		return usuario;
	}
}
