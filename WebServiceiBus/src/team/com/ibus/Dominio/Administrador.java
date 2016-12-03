package team.com.ibus.Dominio;

/**
 * Created by User on 17/11/2016.
 */

public class Administrador extends Usuario{

	private Integer id;
	private Integer cpf;
    private Integer codigoDeAcesso;
    
	public Administrador(String nome, String login, String senha, Integer cpf, Integer codigoAcesso) {
		super(nome, login, senha);
		this.cpf = cpf;
		this.codigoDeAcesso = codigoAcesso;
	}

	public Integer getCpf() {
		return cpf;
	}

	public void setCpf(Integer cpf) {
		this.cpf = cpf;
	}

	public Integer getCodigoDeAcesso() {
		return codigoDeAcesso;
	}

	public void setCodigoDeAcesso(Integer codigoDeAcesso) {
		this.codigoDeAcesso = codigoDeAcesso;
	}

	public Integer getId() {
		return id;
	}
	
	
	

}
