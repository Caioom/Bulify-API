package com.bulefy.api.services;

import static com.bulefy.api.builders.UsuarioBuilder.umUsuario;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.bulefy.api.exceptions.UsuarioException;
import com.bulefy.api.models.Usuario;
import com.bulefy.api.repositories.LembreteRepository;
import com.bulefy.api.repositories.UsuarioRepository;
import com.bulefy.api.services.impl.UsuarioServiceImpl;

public class UsuarioServiceTest {
	
	@InjectMocks
	private UsuarioServiceImpl usuarioService;
	
	@Mock
	private UsuarioRepository usuarioRepository;
	
	@Mock 
	private LembreteRepository lembreteRepository;
	
	@BeforeEach 
	public void setUp()  {
		initMocks(this);
	}
	
	@Test
	public void devePersistirUsuario() throws UsuarioException { 
		Usuario usuario = umUsuario().agora();
		when(this.usuarioRepository.save(Mockito.any(Usuario.class))).thenReturn(usuario);
		
		this.usuarioService.persistir(usuario);
		
		verify(this.usuarioRepository, times(1)).save(usuario);
	}
	
	@Test
	public void naoDevePersistirUsuariosComEmailsIguais() {
		Usuario usuario = umUsuario().agora();
		when(this.usuarioRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(usuario));
		
		try {
			this.usuarioService.persistir(usuario);
			Assertions.fail();
		} catch (UsuarioException e) {
			assertThat(e.getMessage()).isEqualTo("Usuário existente");
		}
	}
	
	@Test
	public void deveBuscarUsuarioPorEmail() {
		Usuario usuario = umUsuario().agora();
		when(this.usuarioRepository.findByEmail(Mockito.anyString()))
									.thenReturn(Optional.of(usuario));
		
		this.usuarioService.buscarPorEmail(Mockito.anyString());
		
		verify(this.usuarioRepository).findByEmail(Mockito.anyString());
	}
}
