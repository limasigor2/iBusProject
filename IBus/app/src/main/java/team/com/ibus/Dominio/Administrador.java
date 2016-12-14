package team.com.ibus.Dominio;

/**
 * Created by User on 17/11/2016.
 */

public class Administrador extends Usuario{

    private String cpf;
    private String codigoDeAcesso;

    public Administrador(String nome, String login, String senha, String cpf, String codigoDeAcesso) {
        super(nome, login, senha);
        this.cpf = cpf;
        this.codigoDeAcesso = codigoDeAcesso;
    }
}
