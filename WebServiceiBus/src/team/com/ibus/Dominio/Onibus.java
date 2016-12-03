package team.com.ibus.Dominio;

/**
 * Created by User on 17/11/2016.
 */

public class Onibus {

	private Integer id;
    private String placa;
    private String cor;
    //private Posicao posicao;
    
    public Onibus(){
    
    }
    
	public Onibus(String placa, String cor) {
		this.placa = placa;
		this.cor = cor;
		id = 0;
		//posicao = null;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id){
		this.id = id;
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
	
//	public Posicao getPosicao() {
//		return posicao;
//	}
//	
//	public void setPosicao(Posicao posicao) {
//		this.posicao = posicao;
//	}
 
    
}
