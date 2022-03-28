package br.ce.wcaquino.utils;

import br.ce.wcaquino.entidades.Filme;
import java.util.List;

public class DescontoNoQuintoFilme extends Desconto {

    public DescontoNoQuintoFilme(Desconto next) {
        super(next);
    }

    @Override
    public void execute(List<Filme> filmes) {
        if (filmes.size() > 4){
            double value = filmes.get(4).getPrecoLocacao()*0.25;
            filmes.get(4).setPrecoLocacao(value);
            this.filmes = filmes;
        }

        if(next != null)
            next.execute(filmes);
    }

}
