package com.bulefy.api.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bulefy.api.exceptions.UsuarioException;
import com.bulefy.api.models.Usuario;
import com.bulefy.api.repositories.UsuarioRepository;
import com.bulefy.api.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	/*@Autowired
	private LembreteRepository lembreteRepository;*/

	@Override
	public Usuario persistir(Usuario usuario) throws UsuarioException {
		if(usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
			throw new UsuarioException("Usuário existente");
		}
		
		usuarioRepository.save(usuario);
		return usuario;
	}

	@Override
	public Optional<Usuario> buscarPorEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}
}
