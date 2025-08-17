package com.tarefas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tarefas.domain.Usuario;
import com.tarefas.dto.UsuarioDto;
import com.tarefas.enums.Funcao;
import com.tarefas.repository.UsuarioRepository;
import com.tarefas.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptEncoder;

	public Usuario cadastrarUsuario(UsuarioDto usuarioDto) {

		if (buscarUsuario(usuarioDto.getUsuario()) != null) {

			throw new IllegalArgumentException("Usuário já existente");

		}

		Usuario usuario = new Usuario(usuarioDto.getUsuario(), bCryptEncoder.encode(usuarioDto.getSenha()),Funcao.USUARIO);

		usuarioRepository.save(usuario);

		return usuario;
	}

	public Usuario buscarUsuario(String usuario) {
		return usuarioRepository.findByUsuario(usuario);
	}

}
