package br.ce.wcaquino.utils;

import br.ce.wcaquino.entidades.Filme;
import java.util.List;

public class DescontoNoSextoFilme extends Desconto {

    public DescontoNoSextoFilme(Desconto next) {
        super(next);
    }

    @Override
    public void execute(final List<Filme> filmes) {
        if (filmes.size() > 5) {
            double value = filmes.get(5).getPrecoLocacao() * 0.0;
            filmes.get(5).setPrecoLocacao(value);
            this.filmes = filmes;
        }

        if (next != null)
            next.execute(filmes);
    }

}
