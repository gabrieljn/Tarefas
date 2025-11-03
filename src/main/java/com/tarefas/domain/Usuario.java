package com.tarefas.domain;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

import com.tarefas.enums.Funcao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Usuario implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;

	@Column(unique = true)
	private String usuario;

	private String senha;

	@Enumerated(EnumType.STRING)
	private Funcao funcao;

	public Usuario() {
		super();
	}

	public Usuario(String usuario, String senha, Funcao funcao) {
		super();
		this.usuario = usuario;
		this.senha = senha;
		this.funcao = funcao;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(funcao, idUsuario, senha, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return funcao == other.funcao && Objects.equals(idUsuario, other.idUsuario)
				&& Objects.equals(senha, other.senha) && Objects.equals(usuario, other.usuario);
	}

}