package br.ce.wcaquino.utils;

import br.ce.wcaquino.entidades.Filme;
import java.util.List;

public class AplicarDescontos{
    private AplicarDescontos(){
    }

    public static List<Filme> aplicar(List<Filme> filmes){
        Desconto desconto =
                new Desconto(new DescontoNoTerceiroFilme(new DescontoNoQuartoFilme(new DescontoNoQuintoFilme(null))));

        desconto.execute(filmes);
        return desconto.getListWithDesconto();
    }
}
