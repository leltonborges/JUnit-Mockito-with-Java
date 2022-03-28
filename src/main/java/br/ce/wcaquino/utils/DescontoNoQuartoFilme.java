package br.ce.wcaquino.utils;

import br.ce.wcaquino.entidades.Filme;
import java.util.List;

public class DescontoNoQuartoFilme extends Desconto {

    public DescontoNoQuartoFilme(Desconto next) {
        super(next);
    }

    @Override
    public void execute(List<Filme> filmes) {
        if (filmes.size() > 3){
            double value = filmes.get(3).getPrecoLocacao()*0.50;
            filmes.get(3).setPrecoLocacao(value);
            this.filmes = filmes;
        }

        if(next != null)
            next.execute(filmes);
    }

}
