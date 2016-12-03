package team.com.ibus.Dominio;

import java.util.Date;

/**
 * Created by User on 17/11/2016.
 */

public class Trajeto {

    private Integer id;
	private Date horaChegadaPrevista;
    private Date horaSaidaPrevista;
    private Onibus onibus;
    private Rota rota;
	
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
}
