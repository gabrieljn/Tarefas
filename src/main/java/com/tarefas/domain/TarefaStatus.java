package com.tarefas.domain;

public enum TarefaStatus {

	CONCLUIDA("concluida"), EM_ANDAMENTO("em_andamento"), CANCELADA("cancelada");

	String valor;

	private TarefaStatus(String valor) {
		this.valor = valor;
	}

}