package com.bulefy.api.builders;

import static java.util.Arrays.asList;

import java.util.Date;

import com.bulefy.api.models.Data;
import com.bulefy.api.models.Lembrete;
import com.bulefy.api.models.Usuario;

public class LembreteBuilder {
	private Lembrete lembrete;
	
	private LembreteBuilder() {}
	
	public static LembreteBuilder umLembrete() { 
		LembreteBuilder builder = new LembreteBuilder();
		builder.lembrete = new Lembrete();
		builder.lembrete.setDataHora(asList(new Data(new Date())));
		return builder;
	}
	
	public LembreteBuilder comUsuario(Usuario usuario) {
		lembrete.setUsuario(usuario);
		return this;
	}
	
	public Lembrete agora() {
		return lembrete;
	}
}
