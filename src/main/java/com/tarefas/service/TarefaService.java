package com.tarefas.service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.NoSuchElementException;

import com.tarefas.domain.Tarefa;
import com.tarefas.dto.TarefaDto;

public interface TarefaService {

	void criarTarefa(TarefaDto tarefaDto);

	Tarefa buscarTarefa(Long id, String usuario) throws AccessDeniedException, NoSuchElementException;

	List<Tarefa> buscarTarefas();

	void apagarTarefa(Long id, String usuario) throws AccessDeniedException, NoSuchElementException;

	void atualizarTarefa(Long id, TarefaDto dto, String usuario) throws AccessDeniedException;

}