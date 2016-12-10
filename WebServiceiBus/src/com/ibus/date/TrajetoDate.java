package com.ibus.date;

import java.util.Date;

public class TrajetoDate {

    private Integer id;
    private Integer idOnibus;
    private Integer idRota;
    private Date horaSaidaPrevista;
    private Date horaChegadaPrevista;
    
    public Integer getId() {
		return id;
	}
	
    public void setId(Integer id) {
		this.id = id;
	}
	
    public Date getHoraChegadaPrevista() {
		return horaChegadaPrevista;
	}
	
    public void setHoraChegadaPrevista(Date horaChegadaPrevista) {
		this.horaChegadaPrevista = horaChegadaPrevista;
	}
	
    public Date getHoraSaidaPrevista() {
		return horaSaidaPrevista;
	}
	
    public void setHoraSaidaPrevista(Date horaSaidaPrevista) {
		this.horaSaidaPrevista = horaSaidaPrevista;
	}

	public Integer getIdOnibus() {
		return idOnibus;
	}

	public void setIdOnibus(Integer idOnibus) {
		this.idOnibus = idOnibus;
	}

	public Integer getIdRota() {
		return idRota;
	}

	public void setIdRota(Integer idRota) {
		this.idRota = idRota;
	}
}
