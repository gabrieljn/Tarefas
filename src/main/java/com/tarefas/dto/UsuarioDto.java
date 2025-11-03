package com.tarefas.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;

public class UsuarioDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank
	private String usuario;

	@NotBlank
	private String senha;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}