package com.bulefy.api.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bulefy.api.dtos.LembreteDTO;
import com.bulefy.api.dtos.Response;
import com.bulefy.api.exceptions.UsuarioException;
import com.bulefy.api.models.Lembrete;
import com.bulefy.api.models.Usuario;
import com.bulefy.api.services.UsuarioService;

@RestController
@RequestMapping("v1")
public class LembreteController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/user/reminder")
	public ResponseEntity<Response<LembreteDTO>> criarLembrete(@Valid @RequestBody LembreteDTO dto, 
																	Authentication auth,
																	BindingResult result) throws UsuarioException {
		Response<LembreteDTO> response = new Response<>();
		
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
		
		usuario.addLembrete(new Lembrete(dto));
		usuarioService.criarLembrete(usuario);
		
		response.setIdUsuario(usuario.getId());
		return ResponseEntity.ok(response);
	}	

}
