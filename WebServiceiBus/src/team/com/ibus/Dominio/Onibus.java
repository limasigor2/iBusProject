package team.com.ibus.Dominio;

/**
 * Created by User on 17/11/2016.
 */

public class Onibus {

    private Integer id;
    private Integer idPosicao;
	private String placa;
    private String cor;
    private Posicao posicao;
	
    public Integer getIdPosicao() {
		return idPosicao;
	}
	public void setIdPosicao(Integer idPosicao) {
		this.idPosicao = idPosicao;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public Integer getId() {
		return id;
	}

    
}
