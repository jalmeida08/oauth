package br.com.jsa.infra.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

@Document(collection = "acesso")
public class Acesso implements GrantedAuthority{


	private static final long serialVersionUID = -1232602904052160097L;
	@Id
	private String id;
	private String descricao;
	private String nome;
	@Version
	private Integer versao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getId() {
		return id;
	}

	public Integer getVersao() {
		return versao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String getAuthority() {
		return this.nome;
	}

}
