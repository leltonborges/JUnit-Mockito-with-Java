package br.ce.wcaquino.utils;

import br.ce.wcaquino.entidades.Filme;
import java.util.List;

public class DescontoNoTerceiroFilme extends Desconto {

    protected DescontoNoTerceiroFilme(Desconto next) {
        super(next);
    }

    @Override
    public void execute(List<Filme> filmes) {
        if(filmes.size() > 2){
            double value = filmes.get(2).getPrecoLocacao()*0.75;
            filmes.get(2).setPrecoLocacao(value);
            this.filmes = filmes;
        }

        if(this.next != null){
            next.execute(filmes);
        }
    }
}
