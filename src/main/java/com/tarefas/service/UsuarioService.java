package com.tarefas.service;

import com.tarefas.domain.Usuario;
import com.tarefas.dto.UsuarioDto;

public interface UsuarioService {
	
	public Usuario cadastrarUsuario(UsuarioDto usuarioDto);
	
	public Usuario buscarUsuario(String usuario);
	
}