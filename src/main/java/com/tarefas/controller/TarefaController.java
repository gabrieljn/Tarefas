package com.tarefas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tarefas.dto.TarefaDto;
import com.tarefas.service.TarefaService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@EnableMethodSecurity
@RequestMapping("tarefas")
public class TarefaController {

	@Autowired
	private TarefaService tarefaService;

	private Jwt getJwt() {
		return (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	@PostMapping
	public ResponseEntity<String> criarTarefa(@RequestBody TarefaDto tarefaDto) {

		if (tarefaDto.getStatus() == null) {

			return ResponseEntity.badRequest().body("O status da tarefa não pode ser nulo.");

		}

		if (tarefaDto.getDataInicio() == null) {

			return ResponseEntity.badRequest().body("A data de início da tarefa não pode ser nula.");

		}

		if (tarefaDto.getTitulo() == null || tarefaDto.getTitulo().isBlank()) {

			return ResponseEntity.badRequest().body("O título da tarefa não pode ser vazio.");

		}

		if (tarefaDto.getDescricao() == null || tarefaDto.getDescricao().isBlank()) {

			return ResponseEntity.badRequest().body("A descrição da tarefa não pode ser vazia.");

		}

		tarefaService.criarTarefa(tarefaDto);

		return ResponseEntity.status(HttpStatus.CREATED).body("Tarefa criada com sucesso.");
	}

	@GetMapping("{id}")
	public ResponseEntity<?> buscarTarefa(@PathVariable Long id) {
		return ResponseEntity.ok(tarefaService.buscarTarefa(id, getJwt().getSubject()));
	}

	@GetMapping
	public ResponseEntity<?> buscarTarefas() {
		return ResponseEntity.ok(tarefaService.buscarTarefas(getJwt().getSubject()));
	}

	@PutMapping("{id}")
	public ResponseEntity<String> atualizarTarefa(@Valid @RequestBody TarefaDto tarefaDto, @PathVariable Long id) {
		tarefaService.atualizarTarefa(id, tarefaDto, getJwt().getSubject());
		return ResponseEntity.ok("Tarefa atualizada com sucesso.");
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deletarTarefa(@PathVariable Long id) {
		tarefaService.apagarTarefa(id, getJwt().getSubject());
		return ResponseEntity.ok("Tarefa apagada com sucesso.");
	}

}
