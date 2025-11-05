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

import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping
	public ResponseEntity<?> cadastrarUsuario(@Valid @RequestBody UsuarioDto usuarioDto) {
		usuarioService.cadastrarUsuario(usuarioDto);
		return ResponseEntity.status(HttpStatus.CREATED).body("Usuário \"" + usuarioDto.getUsuario() + "\" criado com sucesso.");
	}

}