package com.tarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tarefas.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByUsuario(String usuarioLogin);

}