package com.tarefas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tarefas.domain.Usuario;
import com.tarefas.service.LoginService;
import com.tarefas.service.UsuarioService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UsuarioService usuarioService;

	public Usuario verificarLogin(String usuarioLogin, String senhaLogin) {

		Usuario usuario = usuarioService.buscarUsuario(usuarioLogin);

		if (usuario == null || bCryptPasswordEncoder.matches(senhaLogin, usuario.getSenha()) == false) {

			throw new BadCredentialsException("Usuário ou senha inválidos");

		}

		return usuario;
	}

}