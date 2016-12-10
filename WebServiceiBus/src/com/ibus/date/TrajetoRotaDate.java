package com.ibus.date;

import java.util.Date;

import com.ibus.dominio.Posicao;

public class TrajetoRotaDate {
	
	private Date horaSaidaPrevista;
	private Date horaChegadaPrevista;
	private Posicao posicaoPartida;
	private Posicao posicaoDestino;
	
	public Date getHoraSaidaPrevista() {
		return horaSaidaPrevista;
	}
	
	public void setHoraSaidaPrevista(Date horaSaidaPrevista) {
		this.horaSaidaPrevista = horaSaidaPrevista;
	}
	
	public Date getHoraChegadaPrevista() {
		return horaChegadaPrevista;
	}
	
	public void setHoraChegadaPrevista(Date horaChegadaPrevista) {
		this.horaChegadaPrevista = horaChegadaPrevista;
	}
	
	public Posicao getPosicaoPartida() {
		return posicaoPartida;
	}
	
	public void setPosicaoPartida(Posicao posicaoPartida) {
		this.posicaoPartida = posicaoPartida;
	}
	
	public Posicao getPosicaoDestino() {
		return posicaoDestino;
	}
	
	public void setPosicaoDestino(Posicao posicaoDestino) {
		this.posicaoDestino = posicaoDestino;
	}
	
	
}
