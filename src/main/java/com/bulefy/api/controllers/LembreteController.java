package com.bulefy.api.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bulefy.api.dtos.LembreteDTO;
import com.bulefy.api.dtos.Response;
import com.bulefy.api.exceptions.UsuarioException;
import com.bulefy.api.models.Lembrete;
import com.bulefy.api.models.Usuario;
import com.bulefy.api.services.LembreteService;
import com.bulefy.api.services.UsuarioService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("v1")
@Api(value="LembreteController", description="O formato de datas é  dd/MM/yyyy-HH:mm  - Não informar id's também")
public class LembreteController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private LembreteService lembreteService;
	
	@PostMapping("/user/reminders")
	public ResponseEntity<Response<LembreteDTO>> criarLembrete(@Valid @RequestBody LembreteDTO lembreteDto, 
																	Authentication auth,
																	BindingResult result) throws UsuarioException {
		Response<LembreteDTO> response = new Response<>();
		this.verificarRemediosInseridos(lembreteDto, result);
		
		if(result.hasErrors()) {
			List<String> errors = result.getAllErrors()
					  					.stream()
					  					.map(e -> e.getDefaultMessage())
					  					.collect(Collectors.toList());
			
			response.setErrors(errors);
			return ResponseEntity.badRequest().body(response);
		}
		
		Usuario usuario = usuarioService.buscarPorEmail(auth.getName())
								.orElseThrow(() -> new UsuarioException("Usuário inexistente"));
		
		Lembrete lembrete = new Lembrete(lembreteDto);
		lembrete.setUsuario(usuario);
		lembreteService.persistir(lembrete);
		
		response.setIdUsuario(usuario.getId());
		return ResponseEntity.ok(response);
	}	
	
	
	private void verificarRemediosInseridos(LembreteDTO lembrete, BindingResult result) {
		if(lembrete.getRemedios().isEmpty()) {
			result.addError(new ObjectError("remedioError", "O lembrete deve possuir o menos um remédio!"));
		}
	}
}
