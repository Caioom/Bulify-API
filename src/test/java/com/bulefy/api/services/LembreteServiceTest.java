package com.bulefy.api.services;

import static com.bulefy.api.builders.LembreteBuilder.umLembrete;
import static com.bulefy.api.builders.UsuarioBuilder.umUsuario;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.bulefy.api.models.Lembrete;
import com.bulefy.api.models.Usuario;
import com.bulefy.api.repositories.LembreteRepository;
import com.bulefy.api.services.impl.LembreteServiceImpl;

public class LembreteServiceTest {
	
	@InjectMocks
	private LembreteServiceImpl lembreteService;
	
	@Mock
	private LembreteRepository lembreteRepository;
	
	@BeforeEach
	public void setUp() {
		initMocks(this);
	}
	
	@Test
	public void deveCriarUmLembrete() {
		Usuario usuario = umUsuario().agora();
		Lembrete lembrete = umLembrete().comUsuario(usuario).agora();
		
		when(this.lembreteRepository.save(Mockito.any(Lembrete.class))).thenReturn(lembrete);
		
		this.lembreteService.persistir(lembrete);
		
		verify(this.lembreteRepository).save(lembrete);
	}
}
