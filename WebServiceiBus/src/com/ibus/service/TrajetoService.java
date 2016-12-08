package com.ibus.service;

import java.util.Date;
import java.util.List;

import com.ibus.dominio.Onibus;
import com.ibus.dominio.Rota;
import com.ibus.dominio.Trajeto;

public class TrajetoService {

	public Boolean cadastrarTrajeto(Date horaChegadaPrevista, Date horaSaidaPrevista, Rota rota, Onibus onibus){
		
		return false;
	}

	public List<Trajeto> listarTrajetos(){

		return null;
	}

	public Boolean atualizarTrajeto(Trajeto trajeto, Rota rota, Onibus onibus, Date horaChegadaPrevista, Date horaSaidaPrevista){
		
		return false;
	}

	public Boolean removerTrajeto(Trajeto trajeto){
		
		return false;
	}

	public void finalizarTrajeto(Trajeto trajeto, Date horaChegadaRealizada, Date horaSaidaRealizada){

	}

	public void gerarRelatorioMensal(Integer mes){

	}
}
