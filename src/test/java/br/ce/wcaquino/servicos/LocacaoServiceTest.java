package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import java.util.Date;
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
    public void test() {
        Usuario usuario = new Usuario("Usuário 1");
        Filme filme = new Filme("Filme 1", 2, 3.0);
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
        Filme filme2 = new Filme("Filme 1", 0, 3.0);

        service.alugarFilme(usuario2, filme2);
    }

    @Test
    public void deveLancaExcencaoChecandoFilmeSemEstoque2() {
        //Exceptions
        Usuario usuario2 = new Usuario("Usuário 1");
        Filme filme2 = new Filme("Filme 1", 0, 3.0);
        try {
            service.alugarFilme(usuario2, filme2);
            fail("Deveria ter lançado uma exceção");
        }catch (Exception e){
            assertThat(e.getMessage(), is("Filme sem estoque"));
        }
    }

    @Test
    public void deveLancaExcencaoChecandoFilmeSemEstoque3() {
        //Exceptions
        Usuario usuario2 = new Usuario("Usuário 1");
        Filme filme2 = new Filme("Filme 1", 0, 3.0);

        exception.expect(RuntimeException.class);
        exception.expectMessage("Filme sem estoque");
        service.alugarFilme(usuario2, filme2);
    }

    @Test
    public void deveLancaExcencaoChecandoFilmeSemEstoque4() {
        //Exceptions
        Usuario usuario2 = new Usuario("Usuário 1");
        Filme filme2 = new Filme("Filme 1", 0, 3.0);

        assertThrows("erro exception", RuntimeException.class, () -> service.alugarFilme(usuario2, filme2));
    }
}