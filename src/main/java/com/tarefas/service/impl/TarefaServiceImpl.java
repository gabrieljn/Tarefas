package com.tarefas.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarefas.controller.dto.TarefaDTO;
import com.tarefas.domain.Tarefa;
import com.tarefas.domain.TarefaStatus;
import com.tarefas.repository.TarefaRepository;
import com.tarefas.service.TarefaMapper;
import com.tarefas.service.TarefaService;

@Service
public class TarefaServiceImpl implements TarefaService {

	@Autowired TarefaRepository tarefaRepository;
	@Autowired TarefaMapper tarefaMapper;

	@Override
	public void SalvarOuAtulizarTarefa(Tarefa tarefa) {
		if (tarefa.getId() == null) {
			tarefa.setStatus(TarefaStatus.EM_ANDAMENTO);
			tarefa.setDataInicio(LocalDateTime.now());
		}
		tarefaRepository.save(tarefa);
	}

	@Override
	public Tarefa BuscarTarefa(Long id) throws NoSuchElementException {
		if (tarefaRepository.findById(id).isEmpty()) {
			throw new NoSuchElementException("Tarefa nao encontrada");
		} else {
			return tarefaRepository.findById(id).get();
		}
	}

	@Override
	public List<Tarefa> BuscarTarefas() {
		return tarefaRepository.findAll();
	}

	@Override
	public void AtualizarTarefa(Long id, TarefaDTO dto) throws NoSuchElementException {
		Tarefa tarefa = BuscarTarefa(id);
		if (dto.getStatus() == TarefaStatus.CONCLUIDA) {
			tarefa.setDataFim(LocalDateTime.now());
		}
		tarefaMapper.DTOToTarefa(dto, tarefa);
		SalvarOuAtulizarTarefa(tarefa);
	}

	@Override
	public void ApagarTarefa(Long id) throws NoSuchElementException {
		BuscarTarefa(id);
		tarefaRepository.deleteById(id);
	}
	
}