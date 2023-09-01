package com.tarefas.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tarefas.controller.dto.TarefaDTO;
import com.tarefas.domain.Tarefa;
import com.tarefas.service.TarefaService;

@RestController
public class TarefaController {

	@Autowired TarefaService tarefaService;

	@PostMapping("/salvar")
	public ResponseEntity<String> SalvarTarefa(@RequestBody Tarefa tarefa) throws MissingServletRequestParameterException {
		if (tarefa.getTitulo() == null || tarefa.getTitulo().isEmpty()) {
			throw new MissingServletRequestParameterException("Titulo", "String");
		} else if (tarefa.getDescricao() == null || tarefa.getDescricao().isEmpty()) {
			throw new MissingServletRequestParameterException("Descricao", "String");
		} else {
			tarefaService.SalvarOuAtulizarTarefa(tarefa);
			return ResponseEntity.ok("Tarefa Salva com sucesso");
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Tarefa> BuscarTarefa(@PathVariable Long id) throws MissingServletRequestParameterException {
		if (id == null) {
			throw new MissingServletRequestParameterException("id", "Long");
		} else {
			try {
				return ResponseEntity.ok(tarefaService.BuscarTarefa(id));				
			} catch (NoSuchElementException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
			}

		}
	}

	@GetMapping("/tarefas")
	public ResponseEntity<List<Tarefa>> BuscarTarefas() {
		return ResponseEntity.ok(tarefaService.BuscarTarefas());
	}

	@PutMapping("/atualizar/{id}")
	public ResponseEntity<String> AtualizarTarefa(@PathVariable Long id, @RequestBody TarefaDTO dto) throws MissingServletRequestParameterException {
		if (id == null) {
			throw new MissingServletRequestParameterException("id", "Long");
		} else {
			try {
				tarefaService.AtualizarTarefa(id, dto);
				return ResponseEntity.ok("Tarefa atualizada com sucesso");
			} catch (NoSuchElementException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
			}
		}
	}

	@DeleteMapping("/apagar/{id}")
	public ResponseEntity<String> ApagarTarefa(@PathVariable Long id) throws MissingServletRequestParameterException {
		if (id == null) {
			throw new MissingServletRequestParameterException("id", "Long");
		} else {
			try {
				tarefaService.ApagarTarefa(id);
				return ResponseEntity.ok("Tarefa apagada");
			} catch (NoSuchElementException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
			}
		}
	}

}
