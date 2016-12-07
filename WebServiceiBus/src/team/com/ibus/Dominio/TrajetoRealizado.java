package team.com.ibus.Dominio;

import java.util.Date;

public class TrajetoRealizado {
	
	private Integer id;
	private Integer trajPrevisto;
	private Date horaSaidaRealizada;
	private Date horaChegadaRealizada;
    
    public Integer getId() {
		return id;
	}
	
    public void setId(Integer id) {
		this.id = id;
	}

	public Date getHoraSaidaRealizada() {
		return horaSaidaRealizada;
	}

	public void setHoraSaidaRealizada(Date horaSaidaRealizada) {
		this.horaSaidaRealizada = horaSaidaRealizada;
	}

	public Date getHoraChegadaRealizada() {
		return horaChegadaRealizada;
	}

	public void setHoraChegadaRealizada(Date horaChegadaRealizada) {
		this.horaChegadaRealizada = horaChegadaRealizada;
	}

	public Integer getTrajPrevisto() {
		return trajPrevisto;
	}

	public void setTrajPrevisto(Integer trajPrevisto) {
		this.trajPrevisto = trajPrevisto;
	}
}
