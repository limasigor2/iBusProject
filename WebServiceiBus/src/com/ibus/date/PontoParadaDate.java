package com.ibus.date;

/**
 * Created by User on 17/11/2016.
 */

public class PontoParadaDate {

    private Integer id;
    private Integer idPosicao;
	private String nome;
    private String descricao;
    private String endereco;
    //private Posicao posicao;
	
    public Integer getId() {
		return id;
	}
	
    public void setId(Integer id) {
		this.id = id;
	}
	
    public String getNome() {
		return nome;
	}
	
    public void setNome(String nome) {
		this.nome = nome;
	}
	
    public String getDescricao() {
		return descricao;
	}
	
    public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getIdPosicao() {
		return idPosicao;
	}

	public void setIdPosicao(Integer idPosicao) {
		this.idPosicao = idPosicao;
	}

//	public Posicao getPosicao() {
//		return posicao;
//	}
//
//	public void setPosicao(Posicao posicao) {
//		this.posicao = posicao;
//	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
    
   
}
