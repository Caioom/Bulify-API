package com.bulefy.api.services.impl;

import static com.bulefy.api.utils.AgendaUtils.agendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bulefy.api.models.Lembrete;
import com.bulefy.api.repositories.LembreteRepository;
import com.bulefy.api.services.LembreteService;

@Service
public class LembreteServiceImpl implements LembreteService {
	
	@Autowired
	private LembreteRepository lembreteRepository;

	@Override
	public Lembrete persistir(Lembrete lembrete) {
		agendar(lembrete);
		return this.lembreteRepository.save(lembrete);
	}
}
