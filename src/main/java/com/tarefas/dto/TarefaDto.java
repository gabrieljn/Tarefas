package com.tarefas.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.tarefas.domain.TarefaStatus;
import com.tarefas.domain.Usuario;

public class TarefaDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String titulo;

	private String descricao;

	private TarefaStatus status;

	private LocalDateTime dataInicio;

	private LocalDateTime dataFim;

	private Usuario usuario;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TarefaStatus getStatus() {
		return status;
	}

	public void setStatus(TarefaStatus status) {
		this.status = status;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDateTime getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}