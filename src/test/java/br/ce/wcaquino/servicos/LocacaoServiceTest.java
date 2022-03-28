package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static br.ce.wcaquino.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class LocacaoServiceTest {
    LocacaoService service;

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup(){
        service = new LocacaoService();
    }

    @Test
    public void deveAlugarFilmeComSucesso() {
        Usuario usuario = new Usuario("Usuário 1");
        List<Filme> filme = Arrays.asList(new Filme("Filme 1", 5, 3.0));
        Locacao locacao = service.alugarFilme(usuario, filme);

        assertEquals(3.0, locacao.getValor(), 0.01);
        assertTrue(isMesmaData(locacao.getDataLocacao(), new Date()));
        assertTrue(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)));

        assertThat(locacao.getValor(), is(3.0));
        assertThat(locacao.getValor(), is(equalTo(3.0)));
        assertThat(locacao.getValor(), is(not(6.0)));
        assertThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
        assertThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));


        //roles
        error.checkThat(locacao.getValor(), is(3.0));
        error.checkThat(locacao.getValor(), is(3.0));
        error.checkThat(locacao.getValor(), is(equalTo(3.0)));
        error.checkThat(locacao.getValor(), is(not(6.0)));
        error.checkThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
        error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
    }

    @Test(expected = RuntimeException.class)
    public void deveLancaExcencaoChecandoFilmeSemEstoque1() {
        //Exceptions
        Usuario usuario2 = new Usuario("Usuário 1");
        List<Filme> filme = Arrays.asList(new Filme("Filme 1", 0, 3.0));

        service.alugarFilme(usuario2, filme);
    }

    @Test
    public void deveLancaExcencaoChecandoFilmeSemEstoque2() {
        //Exceptions
        Usuario usuario2 = new Usuario("Usuário 1");
        List<Filme> filme = Arrays.asList(new Filme("Filme 1", 0, 3.0));
        try {
            service.alugarFilme(usuario2, filme);
            fail("Deveria ter lançado uma exceção");
        }catch (Exception e){
            assertThat(e.getMessage(), is("Filme sem estoque"));
        }
    }

    @Test
    public void deveLancaExcencaoChecandoFilmeSemEstoque3() {
        //Exceptions
        Usuario usuario2 = new Usuario("Usuário 1");
        List<Filme> filme2 = Arrays.asList(new Filme("Filme 1", 0, 3.0));

        exception.expect(RuntimeException.class);
        exception.expectMessage("Filme sem estoque");
        service.alugarFilme(usuario2, filme2);
    }

    @Test
    public void deveLancaExcencaoChecandoFilmeSemEstoque4() {
        //Exceptions
        Usuario usuario2 = new Usuario("Usuário 1");
        List<Filme> filme = Arrays.asList(new Filme("Filme 1", 0, 3.0));

        assertThrows("erro exception", RuntimeException.class, () -> service.alugarFilme(usuario2, filme));
    }

    @Test
    public void devePagar75PctNoFilme3(){
        Usuario user = new Usuario("User 1");
        List<Filme> filmes = Arrays
                .asList(new Filme("Filme 1", 4, 4.5),
                        new Filme("Filme 2", 1, 15.5),
                        new Filme("Filme 3", 4, 10.0));

        Locacao locacao = service.alugarFilme(user, filmes);

        assertThat(locacao.getValor(), is(27.5));
    }

    @Test
    public void devePagar50PctNoFilme4(){
        Usuario user = new Usuario("User 1");
        List<Filme> filmes = Arrays
                .asList(new Filme("Filme 1", 4, 4.5),
                        new Filme("Filme 2", 1, 15.5),
                        new Filme("Filme 3", 4, 10.0),
                        new Filme("Filme 4", 4, 10.0));

        Locacao locacao = service.alugarFilme(user, filmes);

        assertThat(locacao.getValor(), is(32.5));
    }

    @Test
    public void devePagar50PctNoFilme5(){
        Usuario user = new Usuario("User 1");
        List<Filme> filmes = Arrays
                .asList(new Filme("Filme 1", 4, 4.5),
                        new Filme("Filme 2", 1, 15.5),
                        new Filme("Filme 3", 4, 10.0),
                        new Filme("Filme 4", 4, 10.0),
                        new Filme("Filme 5", 4, 10.0));

        Locacao locacao = service.alugarFilme(user, filmes);

        assertThat(locacao.getValor(), is(35.0));
    }
}