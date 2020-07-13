package com.bulefy.api.utils;

import static com.bulefy.api.builders.LembreteBuilder.umLembrete;
import static com.bulefy.api.utils.AgendaUtils.agendar;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.bulefy.api.models.Lembrete;

public class AgendaUtilsTest {
	
	@Mock
	private AgendaUtils agenda;
	
	@BeforeEach
	public void setUp() {
		initMocks(this);
	}
	
	@Test
	public void deveAgendarParaLembrete() {
		//cenario 
		Lembrete lembrete = umLembrete().agora();
		
		//acao 
		agendar(lembrete);
		
		//verificacao
		verify(this.agenda, times(1)).agendar(lembrete);
	}
}
