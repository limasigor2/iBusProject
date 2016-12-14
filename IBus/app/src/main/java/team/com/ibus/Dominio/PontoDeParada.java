package team.com.ibus.Dominio;

/**
 * Created by User on 17/11/2016.
 */

public class PontoDeParada {

    private int id;
    private String nome;
    private String descricao;
    private String endereco;
    private Posicao posicao;

    public PontoDeParada() {
    }

    public PontoDeParada(String nome, String descricao, String endereco, Posicao posicao) {

        this.nome = nome;
        this.descricao = descricao;
        this.posicao = posicao;
    }

    public PontoDeParada(int id, String nome, String descricao, String endereco, Posicao posicao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.endereco = endereco;
        this.posicao = posicao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }
}
