package com.bulefy.api.controllers;

import static com.bulefy.api.utils.PasswordUtils.encode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bulefy.api.dtos.LembreteDTO;
import com.bulefy.api.dtos.Response;
import com.bulefy.api.dtos.UsuarioDTO;
import com.bulefy.api.exceptions.UsuarioException;
import com.bulefy.api.models.Usuario;
import com.bulefy.api.services.UsuarioService;

@RestController
@RequestMapping("v1")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/register")
	public ResponseEntity<Response<Long>> criarNovo(@Valid @RequestBody UsuarioDTO usuarioDto, BindingResult result) {
		Response<Long> response = new Response<>();
		if(result.hasErrors()) {
			List<String> errors = result.getAllErrors()
													.stream()
													.map(e -> e.getDefaultMessage())
													.collect(Collectors.toList());
			response.setErrors(errors);
			return ResponseEntity.badRequest().body(response);
		}
		
		Usuario usuario;
		try {
			Usuario user = new Usuario(usuarioDto);
			user.setSenha(encode(usuarioDto.getSenha()));
			usuario = usuarioService.persistir(user);
		} catch (UsuarioException e) {
			response.setErrors(Arrays.asList(e.getMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setIdUsuario(usuario.getId());
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/user/reminders")
	public ResponseEntity<Response<List<LembreteDTO>>> buscarTodosLembretes(@RequestParam("email") String email) {
		Response<List<LembreteDTO>> response = new Response<>();
		
		Usuario usuario;
		try {
			//Substituir após implementação do JWT, irá buscar pelo Authentication do Spring Security
			usuario = usuarioService.buscarPorEmail(email)
											.orElseThrow(() -> new UsuarioException("Você não está apto para esta busca"));
		} catch (UsuarioException e) {
			response.setErrors(Arrays.asList(e.getMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		List<LembreteDTO> lembretes = usuario.getLembretes()
												.stream().map(LembreteDTO::new)
												.collect(Collectors.toList());
		response.setIdUsuario(usuario.getId());
		response.setData(lembretes);
		return ResponseEntity.ok(response);
	}
}
