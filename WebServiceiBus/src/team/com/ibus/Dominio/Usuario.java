package team.com.ibus.Dominio;

/**
 * Created by User on 17/11/2016.
 */

public class Usuario {

    private Integer id;
	private String nome;
    private String login;
    private String senha;
	
    public Usuario(){
    	
    }
    
    public Usuario(String nome, String login, String senha) {
		this.id = 0;
    	this.nome = nome;
		this.login = login;
		this.senha = senha;
	}
 
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

    public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
    
}
