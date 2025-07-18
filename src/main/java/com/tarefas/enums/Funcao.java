package com.tarefas.enums;

public enum Funcao {

	ADMIN("admin"), USUARIO("usuario");

	String funcao;

	private Funcao(String funcao) {
		this.funcao = funcao;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

}