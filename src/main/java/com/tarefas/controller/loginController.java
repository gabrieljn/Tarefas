package com.tarefas.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.service.TokenService;
import com.tarefas.domain.Usuario;
import com.tarefas.service.LoginService;

@RestController
@CrossOrigin("*")
@RequestMapping("login")
public class loginController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<?> login(@RequestBody Map<String, String> login) {

		try {

			String usuario = login.get("usuario");
			String senha = login.get("senha");

			if (usuario == null || senha == null || usuario.isBlank() || senha.isBlank()) {

				return ResponseEntity.badRequest().body("Usuário e senha são obrigatórios.");

			}

			Usuario usuarioValidado = loginService.verificarLogin(usuario, senha);

			Map<String, String> payload = new HashMap<>();
			payload.put("usuario", usuarioValidado.getUsuario());
			payload.put("permissoes", usuarioValidado.getFuncao().getFuncao().toString());

			return ResponseEntity.ok(tokenService.gerarToken(payload, 40L));

		} catch (BadCredentialsException e) {

			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos.");

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor.");

		}

	}

}