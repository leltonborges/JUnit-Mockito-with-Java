package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.AplicarDescontos;
import br.ce.wcaquino.utils.DataUtils;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static br.ce.wcaquino.utils.DataUtils.adicionarDias;

public class LocacaoService {

    public Locacao alugarFilme(Usuario usuario, List<Filme> filmes) {
        if (filmes.stream().anyMatch(f -> f.getEstoque() == 0)) {
            throw new RuntimeException("Filme sem estoque");
        }
        Locacao locacao = new Locacao();
        filmes = AplicarDescontos.aplicar(filmes);
        locacao.setFilme(filmes);
        locacao.setUsuario(usuario);
        locacao.setDataLocacao(new Date());
        locacao.setValor(filmes.stream().mapToDouble(Filme::getPrecoLocacao).sum());

        //Entrega no dia seguinte
        Date dataEntrega = new Date();
        dataEntrega = adicionarDias(dataEntrega, 1);
        if(DataUtils.verificarDiaSemana(dataEntrega, Calendar.SUNDAY))
            dataEntrega= adicionarDias(dataEntrega, 1);
        locacao.setDataRetorno(dataEntrega);

        //Salvando a locacao...	
        //TODO adicionar método para salvar

        return locacao;
    }
}