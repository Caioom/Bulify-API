package com.bulefy.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bulefy.api.models.Usuario;
import com.bulefy.api.repositories.UsuarioRepository;

@Service
public class CustomDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(username);
		
		List<GrantedAuthority> usuarioAuthorities = AuthorityUtils.createAuthorityList("ROLE_USUARIO");
		return new User(usuario.get().getEmail(), usuario.get().getSenha(), usuarioAuthorities);
	}

}
