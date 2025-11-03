package com.tarefas.service;

import java.util.List;

import com.tarefas.domain.Tarefa;
import com.tarefas.dto.TarefaDto;

public interface TarefaService {

	void criarTarefa(TarefaDto tarefaDto);

	Tarefa buscarTarefa(Long id, String usuario);

	List<Tarefa> buscarTarefas(String nomeUsuario);

	void apagarTarefa(Long id, String usuario);

	void atualizarTarefa(Long id, TarefaDto dto, String usuario);

}