package team.com.ibus.Dominio;

import java.util.List;

/**
 * Created by User on 17/11/2016.
 */

public class Rota {

	private Integer id;
	private Integer posPartida;
	private Integer posDestino;
    private List<PontoDeParada> listaPontoDeParada;
	
    public Integer getId() {
		return id;
	}
	
    public void setId(Integer id) {
		this.id = id;
	}
	
    public Integer getPosPartida() {
		return posPartida;
	}
	
    public void setPosPartida(Integer posPartida) {
		this.posPartida = posPartida;
	}
	
    public Integer getPosDestino() {
		return posDestino;
	}
	
    public void setPosDestino(Integer posDestino) {
		this.posDestino = posDestino;
	}
	
    public List<PontoDeParada> getListaPontoDeParada() {
		return listaPontoDeParada;
	}
	
    public void setListaPontoDeParada(List<PontoDeParada> listaPontoDeParada) {
		this.listaPontoDeParada = listaPontoDeParada;
	}

}
