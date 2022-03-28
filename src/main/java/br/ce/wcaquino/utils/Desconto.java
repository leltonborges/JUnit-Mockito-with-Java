package br.ce.wcaquino.utils;

import br.ce.wcaquino.entidades.Filme;
import java.util.List;

public class Desconto {
    protected final Desconto next;
    protected List<Filme> filmes;

    protected Desconto(Desconto next) {
        this.next = next;
    }

    protected void execute(List<Filme> filmes){
        this.filmes = filmes;
        if(next != null){
            this.next.execute(this.filmes);
        }
    }

    public List<Filme> getListWithDesconto(){
        return this.filmes;
    }
}
