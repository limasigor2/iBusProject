package com.ibus.date;

/**
 * Created by User on 17/11/2016.
 */

public class OnibusDate {

	private Integer id;
    private String placa;
    private String cor;
    
    public OnibusDate(){
    
    }
    
	public OnibusDate(String placa, String cor) {
		this.placa = placa;
		this.cor = cor;
		id = 0;
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
    
}
