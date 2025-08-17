package com.tarefas.controller;

import java.nio.file.AccessDeniedException;
import java.util.NoSuchElementException;

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

		if (tarefaDto.getTitulo().isBlank() || tarefaDto.getDescricao().isBlank()) {

			return ResponseEntity.badRequest().body("Título e descrição são obrigatórios.");

		}

		try {

			tarefaService.criarTarefa(tarefaDto);
			return ResponseEntity.status(HttpStatus.CREATED).body("Tarefa criada com sucesso.");

		} catch (NoSuchElementException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor.");

		}

	}

	@GetMapping("{id}")
	public ResponseEntity<?> buscarTarefa(@PathVariable Long id) {

		try {

			return ResponseEntity.ok(tarefaService.buscarTarefa(id, getJwt().getSubject()));

		} catch (NoSuchElementException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

		} catch (AccessDeniedException e) {

			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor.");

		}

	}

	@GetMapping
	public ResponseEntity<?> buscarTarefas() {

		try {

			Jwt jwt = getJwt();

			if (!"admin".equals(jwt.getClaimAsString("scope"))) {

				return ResponseEntity.status(HttpStatus.FORBIDDEN)
						.body("Você não possui permissão para acessar essa função.");

			}

			return ResponseEntity.ok(tarefaService.buscarTarefas());

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor.");

		}

	}

	@PutMapping("{id}")
	public ResponseEntity<String> atualizarTarefa(@PathVariable Long id, @RequestBody TarefaDto tarefaDto) {

		try {

			tarefaService.atualizarTarefa(id, tarefaDto, getJwt().getSubject());
			return ResponseEntity.ok("Tarefa atualizada com sucesso.");

		} catch (NoSuchElementException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

		} catch (AccessDeniedException e) {

			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor.");

		}

	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deletarTarefa(@PathVariable Long id) {

		try {

			tarefaService.apagarTarefa(id, getJwt().getSubject());
			return ResponseEntity.ok("Tarefa apagada com sucesso.");

		} catch (NoSuchElementException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

		} catch (AccessDeniedException e) {

			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor.");

		}

	}

}