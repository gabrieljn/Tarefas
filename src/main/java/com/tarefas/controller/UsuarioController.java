package com.tarefas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tarefas.dto.UsuarioDto;
import com.tarefas.service.UsuarioService;

@RestController
@CrossOrigin("*")
@RequestMapping("usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping
	public ResponseEntity<?> cadastrarUsuario(@RequestBody UsuarioDto usuarioDto) {

		try {

			if (usuarioDto.getUsuario() == null || usuarioDto.getUsuario().isBlank() || usuarioDto.getSenha() == null
					|| usuarioDto.getSenha().isBlank()) {

				return ResponseEntity.badRequest().body("Usuário e senha são obrigatórios.");

			}

			usuarioService.cadastrarUsuario(usuarioDto);

			return ResponseEntity.status(HttpStatus.CREATED)
					.body("Usuário \"" + usuarioDto.getUsuario() + "\" criado com sucesso.");

		} catch (IllegalArgumentException e) {
	
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor.");

		}

	}

}