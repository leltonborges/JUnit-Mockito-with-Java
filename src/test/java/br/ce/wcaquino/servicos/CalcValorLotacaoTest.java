package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
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

    private Usuario usuario;
    private LocacaoService service;

    @Before
    public void setup() {
        service = new LocacaoService();
        usuario = new Usuario("Usuário 1");
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getParametros() {
        return Arrays.asList(new Object[][]{
                {Arrays.asList(new Filme("Filme 1", 2, 4.0), new Filme("Filme 2", 2, 4.0)), 8.0},
                {Arrays.asList(new Filme("Filme 1", 2, 4.0), new Filme("Filme 2", 2, 4.0), new Filme("Filme 3", 2, 4.0)), 11.0},
                {Arrays.asList(new Filme("Filme 1", 2, 4.0), new Filme("Filme 2", 2, 4.0), new Filme("Filme 3", 2, 4.0), new Filme("Filme 4", 2, 4.0)), 13.0},
                {Arrays.asList(new Filme("Filme 1", 2, 4.0), new Filme("Filme 2", 2, 4.0), new Filme("Filme 3", 2, 4.0), new Filme("Filme 4", 2, 4.0), new Filme("Filme 5", 2, 4.0)), 14.0},
                {Arrays.asList(new Filme("Filme 1", 2, 4.0), new Filme("Filme 2", 2, 4.0), new Filme("Filme 3", 2, 4.0), new Filme("Filme 4", 2, 4.0), new Filme("Filme 5", 2, 4.0), new Filme("Filme 6", 2, 4.0)), 14.0},
                {Arrays.asList(new Filme("Filme 1", 2, 4.0), new Filme("Filme 2", 2, 4.0), new Filme("Filme 3", 2, 4.0), new Filme("Filme 4", 2, 4.0), new Filme("Filme 5", 2, 4.0), new Filme("Filme 6", 2, 4.0), new Filme("Filme 7", 2, 4.0)), 18.0},
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
