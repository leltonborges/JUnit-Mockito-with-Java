package br.ce.wcaquino.servicos;

import br.ce.wcaquino.dao.LocacaoDao;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.matchers.DiaSemanaMatcher;
import br.ce.wcaquino.utils.DataUtils;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import java.util.*;
import static br.ce.wcaquino.builders.FilmeBuilder.filmeBuilder;
import static br.ce.wcaquino.builders.UsuarioBuilder.umUsuario;
import static br.ce.wcaquino.matchers.MatcherProprios.*;
import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static br.ce.wcaquino.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class LocacaoServiceTest {
    private LocacaoService service;
    private Usuario usuario;
    private LocacaoDao locacaoDao;

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup(){
        service = new LocacaoService();
        usuario = umUsuario().build();
        locacaoDao = Mockito.mock(LocacaoDao.class);
        service.setLocacaoDao(locacaoDao);
    }

    @Test
    public void deveAlugarFilmeComSucesso() {
        Assume.assumeFalse(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));

        List<Filme> filme = Arrays.asList(filmeBuilder().build());
        Locacao locacao = service.alugarFilme(usuario, filme);

        assertEquals(3.0, locacao.getValor(), 0.01);
        assertTrue(isMesmaData(locacao.getDataLocacao(), new Date()));
        assertThat(locacao.getDataLocacao(), ehHoje()); //custom
        assertTrue(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)));

        assertThat(locacao.getValor(), is(3.0));
        assertThat(locacao.getValor(), is(equalTo(3.0)));
        assertThat(locacao.getValor(), is(not(6.0)));
        assertThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
        assertThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));


        //roles
        error.checkThat(locacao.getValor(), is(3.0));
        error.checkThat(locacao.getValor(), is(3.0));
        error.checkThat(locacao.getValor(), is(equalTo(3.0)));
        error.checkThat(locacao.getValor(), is(not(6.0)));
        error.checkThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
        error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
        error.checkThat(locacao.getDataLocacao(), ehHoje());
//        error.checkThat(locacao.getDataLocacao(), ehHojeComDiferenciaDeDias(1));
    }

    @Test(expected = RuntimeException.class)
    public void deveLancaExcencaoChecandoFilmeSemEstoque1() {
        //Exceptions
        List<Filme> filme = Collections.singletonList(filmeBuilder().semEstoque().build());

        service.alugarFilme(usuario, filme);
    }

    @Test
    public void deveLancaExcencaoChecandoFilmeSemEstoque2() {
        //Exceptions
        List<Filme> filme = Collections.singletonList(filmeBuilder().semEstoque().build());
        try {
            service.alugarFilme(usuario, filme);
            fail("Deveria ter lançado uma exceção");
        }catch (Exception e){
            assertThat(e.getMessage(), is("Filme sem estoque"));
        }
    }

    @Test
    public void deveLancaExcencaoChecandoFilmeSemEstoque3() {
        //Cenario
        //Exceptions
        List<Filme> filme2 = Collections.singletonList(filmeBuilder().semEstoque().build());

        //Verificação
        exception.expect(RuntimeException.class);
        exception.expectMessage("Filme sem estoque");

        //acao
        service.alugarFilme(usuario, filme2);
    }

    @Test
    public void deveLancaExcencaoChecandoFilmeSemEstoque4() {
        //Exceptions
        List<Filme> filme = Collections.singletonList(filmeBuilder().semEstoque().build());

        assertThrows("erro exception", RuntimeException.class, () -> service.alugarFilme(usuario, filme));
    }

    @Test
    public void devePagar75PctNoFilme3(){
        List<Filme> filmes = Arrays
                .asList(new Filme("Filme 1", 4, 4.5),
                        new Filme("Filme 2", 1, 15.5),
                        new Filme("Filme 3", 4, 10.0));

        Locacao locacao = service.alugarFilme(usuario, filmes);

        assertThat(locacao.getValor(), is(27.5));
    }

    @Test
    public void devePagar50PctNoFilme4(){
        List<Filme> filmes = Arrays
                .asList(new Filme("Filme 1", 4, 4.5),
                        new Filme("Filme 2", 1, 15.5),
                        new Filme("Filme 3", 4, 10.0),
                        new Filme("Filme 4", 4, 10.0));

        Locacao locacao = service.alugarFilme(usuario, filmes);

        assertThat(locacao.getValor(), is(32.5));
    }

    @Test
    public void devePagar50PctNoFilme5(){
        List<Filme> filmes = Arrays
                .asList(new Filme("Filme 1", 4, 4.5),
                        new Filme("Filme 2", 1, 15.5),
                        new Filme("Filme 3", 4, 10.0),
                        new Filme("Filme 4", 4, 10.0),
                        new Filme("Filme 5", 4, 10.0));

        Locacao locacao = service.alugarFilme(usuario, filmes);

        assertThat(locacao.getValor(), is(35.0));
    }

    @Test
    public void devePagar50PctNoFilme6(){
        List<Filme> filmes = Arrays
                .asList(new Filme("Filme 1", 4, 4.5),
                        new Filme("Filme 2", 1, 15.5),
                        new Filme("Filme 3", 4, 10.0),
                        new Filme("Filme 4", 4, 10.0),
                        new Filme("Filme 5", 4, 10.0),
                        new Filme("Filme 6", 4, 10.0));

        Locacao locacao = service.alugarFilme(usuario, filmes);

        assertThat(locacao.getValor(), is(35.0));
    }

    @DisplayName("Não deve devolver filmes no domingo")
    @Test
    public void deveDevolverNaSegundaAoAlugarNoSabado(){
        Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));
        List<Filme> filmes =  Arrays
                .asList(new Filme("Filme 1", 4, 4.5),
                        new Filme("Filme 6", 4, 10.0));

        Locacao locacao = service.alugarFilme(usuario, filmes);
        boolean ehSegunda = DataUtils.verificarDiaSemana(locacao.getDataRetorno(), Calendar.MONDAY);
        assertTrue(ehSegunda);
        assertThat(locacao.getDataRetorno(), new DiaSemanaMatcher(Calendar.MONDAY));
        assertThat(locacao.getDataRetorno(), caiEm(Calendar.MONDAY));
        assertThat(locacao.getDataRetorno(), caiNumaSegunda());

    }
}