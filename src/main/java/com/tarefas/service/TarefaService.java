package com.tarefas.service;

import java.util.List;
import java.util.NoSuchElementException;

import com.tarefas.controller.dto.TarefaDTO;
import com.tarefas.domain.Tarefa;

public interface TarefaService {

	void SalvarOuAtulizarTarefa(Tarefa tarefa);

	Tarefa BuscarTarefa(Long id) throws NoSuchElementException;

	List<Tarefa> BuscarTarefas();

	void ApagarTarefa(Long id) throws NoSuchElementException;

	void AtualizarTarefa(Long id, TarefaDTO dto) throws NoSuchElementException ;

}
