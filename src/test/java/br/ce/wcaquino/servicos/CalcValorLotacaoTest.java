package br.ce.wcaquino.servicos;

import br.ce.wcaquino.dao.LocacaoDao;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class CalcValorLotacaoTest {
    @Parameterized.Parameter
    public List<Filme> filmes;
    @Parameterized.Parameter(1)
    public Double valorLocacao;
    @Parameterized.Parameter(2)
    public String cenario;

    private Usuario usuario;
    private LocacaoService service;
    private LocacaoDao locacaoDao;
    private SPCService spcService;

    @Before
    public void setup() {
        service = new LocacaoService();
        usuario = new Usuario("Usuário 1");

        locacaoDao = Mockito.mock(LocacaoDao.class);
        spcService = Mockito.mock(SPCService.class);

        service.setLocacaoDao(locacaoDao);
        service.setSpcService(spcService);
    }

    @Parameterized.Parameters(name = "Teste {index} = {2}")
    public static Collection<Object[]> getParametros() {
        return Arrays.asList(new Object[][]{
                {Arrays.asList(new Filme("Filme 1", 2, 4.0), new Filme("Filme 2", 2, 4.0)), 8.0, "Sem Descontos"},
                {Arrays.asList(new Filme("Filme 1", 2, 4.0), new Filme("Filme 2", 2, 4.0), new Filme("Filme 3", 2, 4.0)), 11.0, "3 filmes: 25%"},
                {Arrays.asList(new Filme("Filme 1", 2, 4.0), new Filme("Filme 2", 2, 4.0), new Filme("Filme 3", 2, 4.0), new Filme("Filme 4", 2, 4.0)), 13.0, "4 filmes: 50%"},
                {Arrays.asList(new Filme("Filme 1", 2, 4.0), new Filme("Filme 2", 2, 4.0), new Filme("Filme 3", 2, 4.0), new Filme("Filme 4", 2, 4.0), new Filme("Filme 5", 2, 4.0)), 14.0, "5 filmes: 75%"},
                {Arrays.asList(new Filme("Filme 1", 2, 4.0), new Filme("Filme 2", 2, 4.0), new Filme("Filme 3", 2, 4.0), new Filme("Filme 4", 2, 4.0), new Filme("Filme 5", 2, 4.0), new Filme("Filme 6", 2, 4.0)), 14.0, "6 filmes: 100%"},
                {Arrays.asList(new Filme("Filme 1", 2, 4.0), new Filme("Filme 2", 2, 4.0), new Filme("Filme 3", 2, 4.0), new Filme("Filme 4", 2, 4.0), new Filme("Filme 5", 2, 4.0), new Filme("Filme 6", 2, 4.0), new Filme("Filme 7", 2, 4.0)), 18.0, "7 filmes: não se aplica"},
        });
    }

    @Test
    public void deveCalcularValorLocacaoConsiderandoDescontos() {
        //Cenario

        // Ação
        Locacao locacao = service.alugarFilme(this.usuario, this.filmes);

        // Verificação
        assertThat(locacao.getValor(), is(valorLocacao));
    }
}
