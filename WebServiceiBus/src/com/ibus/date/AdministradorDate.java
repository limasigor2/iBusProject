package com.ibus.date;

/**
 * Created by User on 17/11/2016.
 */

public class AdministradorDate{

	private Integer id;
	private String nome;
	private String login;
	private String senha;
	private Integer cpf;
    private Integer codigoDeAcesso;
    
	public AdministradorDate(String nome, String login, String senha, Integer cpf, Integer codigoAcesso) {
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.cpf = cpf;
		this.codigoDeAcesso = codigoAcesso;
	}

	public Integer getId() {
		return id;
	}
	
	public Integer getCpf() {
		return cpf;
	}

	public void setCpf(Integer cpf) {
		this.cpf = cpf;
	}

	public Integer getCodigoDeAcesso() {
		return codigoDeAcesso;
	}

	public void setCodigoDeAcesso(Integer codigoDeAcesso) {
		this.codigoDeAcesso = codigoDeAcesso;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
