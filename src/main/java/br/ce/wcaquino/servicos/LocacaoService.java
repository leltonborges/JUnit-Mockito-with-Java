package br.ce.wcaquino.servicos;

import br.ce.wcaquino.dao.LocacaoDao;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exception.LocadoraException;
import br.ce.wcaquino.utils.AplicarDescontos;
import br.ce.wcaquino.utils.DataUtils;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static br.ce.wcaquino.utils.DataUtils.adicionarDias;

public class LocacaoService {

    private LocacaoDao locacaoDao;
    private SPCService spcService;
    private EmailService emailService;

    public Locacao alugarFilme(Usuario usuario, List<Filme> filmes) {
        if (filmes.stream().anyMatch(f -> f.getEstoque() == 0)) {
            throw new RuntimeException("Filme sem estoque");
        }

        boolean negativado;

        try {
            negativado = spcService.possuiNegativacao(usuario);
        } catch (Exception e) {
            throw new LocadoraException("Problemas com SPC, tente novamente!");
        }
        if (negativado) {
            throw new LocadoraException("Usuário negativado");
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
        if (DataUtils.verificarDiaSemana(dataEntrega, Calendar.SUNDAY))
            dataEntrega = adicionarDias(dataEntrega, 1);
        locacao.setDataRetorno(dataEntrega);

        //Salvando a locacao...	
        locacaoDao.salvar(locacao);

        return locacao;
    }

    public void notificarAtrasos() {
        List<Locacao> locacaos = locacaoDao.obterLocacoesPendentes();
        locacaos.stream()
                .filter(l -> l.getDataRetorno().before(new Date()))
                .forEach(l -> emailService.notificarAtraso(l.getUsuario()));
    }

    public void prorrogarLocacao(Locacao locacao, int dias){
        Locacao novaLocacao = new Locacao();
        novaLocacao.setUsuario(locacao.getUsuario());
        novaLocacao.setFilme(locacao.getFilme());
        novaLocacao.setDataLocacao(new Date());
        novaLocacao.setDataRetorno(DataUtils.obterDataComDiferencaDias(dias));
        novaLocacao.setValor(locacao.getValor() * dias);

        locacaoDao.salvar(novaLocacao);
    }

}