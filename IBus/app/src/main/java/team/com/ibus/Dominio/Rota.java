package team.com.ibus.Dominio;

import java.util.List;

/**
 * Created by User on 17/11/2016.
 */

public class Rota {

    private Posicao posicaoOrigem;
    private Posicao posicaoDestino;
    private List<PontoDeParada> listaPontoDeParada;
    private int id;

    public Rota() {
    }

    public Rota(int id) {
        this.id = id;
    }

    public Rota(Posicao posicaoOrigem, Posicao posicaoDestino, List<PontoDeParada> listaPontoDeParada) {
        this.posicaoOrigem = posicaoOrigem;
        this.posicaoDestino = posicaoDestino;
        this.listaPontoDeParada = listaPontoDeParada;
    }

    public Rota(Posicao posicaoOrigem, Posicao posicaoDestino, List<PontoDeParada> listaPontoDeParada, int id) {
        this.posicaoOrigem = posicaoOrigem;
        this.posicaoDestino = posicaoDestino;
        this.listaPontoDeParada = listaPontoDeParada;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Posicao getPosicaoOrigem() {
        return posicaoOrigem;
    }

    public void setPosicaoOrigem(Posicao posicaoOrigem) {
        this.posicaoOrigem = posicaoOrigem;
    }

    public Posicao getPosicaoDestino() {
        return posicaoDestino;
    }

    public void setPosicaoDestino(Posicao posicaoDestino) {
        this.posicaoDestino = posicaoDestino;
    }

    public List<PontoDeParada> getListaPontoDeParada() {
        return listaPontoDeParada;
    }

    public void setListaPontoDeParada(List<PontoDeParada> listaPontoDeParada) {
        this.listaPontoDeParada = listaPontoDeParada;
    }
}
