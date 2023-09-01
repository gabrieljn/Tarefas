package com.tarefas.controller.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.tarefas.domain.TarefaStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class TarefaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String titulo;
	private String descricao;
	@Enumerated(EnumType.STRING)
	private TarefaStatus status;
	private LocalDateTime dataInicio;
	private LocalDateTime dataFim;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

}