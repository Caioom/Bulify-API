package com.bulefy.api.services;

import java.util.Optional;

import com.bulefy.api.exceptions.UsuarioException;
import com.bulefy.api.models.Lembrete;
import com.bulefy.api.models.Usuario;

public interface UsuarioService {
	public Usuario persistir(Usuario usuario) throws UsuarioException;
	
	public Optional<Usuario> buscarPorEmail(String email);
	
	public Lembrete criarLembrete(Usuario usuario);
}
