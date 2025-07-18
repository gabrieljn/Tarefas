package com.tarefas.service;

import com.tarefas.domain.Usuario;

public interface LoginService {

	public Usuario verificarLogin(String usuarioLogin, String senhaLogin);

}