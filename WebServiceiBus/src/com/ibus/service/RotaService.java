package com.ibus.service;

import java.util.List;

import com.ibus.dominio.Posicao;
import com.ibus.dominio.Rota;
import com.ibus.dominio.PontoParada;

public class RotaService {
	
	public Boolean cadastrarRota(Posicao posicaoOrigem, Posicao posicaoDestino, List<PontoParada> listaPontosDeParada){
		
		return false;
	}

	public List<Rota> listarRotas(){
			
		return null;
	}

	public Boolean removerRota(Rota rota){
		
		return false;
	}

	public Boolean cadastrarPontoDeParada(String nome, String descricao, Posicao posicao){
		
		return false;
	}

	public List<PontoParada> listarPontoDeParada(){

		return null;
	}

	public Boolean atualizarPontoDeParada(PontoParada pontoDeParada, String novoNome, String novaDescricao){
		
		return false;
	}

	public Boolean removerPontoDeParada(PontoParada pontoDeParada){
		
		return false;
	}
}
