package com.tarefas.service.impl;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarefas.domain.Tarefa;
import com.tarefas.domain.TarefaStatus;
import com.tarefas.domain.Usuario;
import com.tarefas.dto.TarefaDto;
import com.tarefas.repository.TarefaRepository;
import com.tarefas.service.TarefaMapper;
import com.tarefas.service.TarefaService;
import com.tarefas.service.UsuarioService;

@Service
public class TarefaServiceImpl implements TarefaService {

	@Autowired
	private TarefaRepository tarefaRepository;

	@Autowired
	private TarefaMapper tarefaMapper;

	@Autowired
	private UsuarioService usuarioService;

	public void criarTarefa(TarefaDto tarefaDto) {

		tarefaDto.setStatus(TarefaStatus.EM_ANDAMENTO);

		tarefaDto.setDataInicio(LocalDateTime.now());

		Usuario usuario = usuarioService.buscarUsuario(tarefaDto.getUsuario().getUsuario());

		if (usuario == null) {

			throw new NoSuchElementException("usuário informado não existe");

		}

		tarefaDto.setUsuario(usuarioService.buscarUsuario(tarefaDto.getUsuario().getUsuario()));

		Tarefa tarefa = new Tarefa(tarefaDto.getTitulo(), tarefaDto.getDescricao(), tarefaDto.getStatus(),tarefaDto.getDataInicio(), tarefaDto.getDataFim(), tarefaDto.getUsuario());

		tarefaRepository.save(tarefa);
	}

	public Tarefa buscarTarefa(Long id, String usuario) throws AccessDeniedException, NoSuchElementException {

		Tarefa tarefa = tarefaRepository.findById(id).get();

		if (tarefa.getId() == null) {

			throw new NoSuchElementException("Tarefa nao encontrada");

		}

		if (!tarefa.getUsuario().getUsuario().equals(usuario)) {

			throw new AccessDeniedException("Tarefa não pertence ao usuário logado");

		}

		return tarefa;

	}

	public List<Tarefa> buscarTarefas() {
		return tarefaRepository.findAll();
	}

	public void atualizarTarefa(Long id, TarefaDto tarefaDto, String usuario) throws AccessDeniedException {
		Tarefa tarefa = buscarTarefa(id, usuario);

		tarefaMapper.DTOToTarefa(tarefaDto, tarefa);

		tarefaRepository.save(tarefa);

	}

	public void apagarTarefa(Long id, String usuario) throws AccessDeniedException, NoSuchElementException {
		Tarefa tarefa;

		tarefa = buscarTarefa(id, usuario);

		tarefaRepository.deleteById(tarefa.getId());
	}

}