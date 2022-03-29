package br.ce.wcaquino.builders;

import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import static br.ce.wcaquino.builders.FilmeBuilder.filmeBuilder;

public class LocacaoBuilder {
    private Locacao locacao;

    private  LocacaoBuilder(){
    }

    public static LocacaoBuilder create(){
        LocacaoBuilder builder = new LocacaoBuilder();
        builder.locacao = new Locacao();
        builder.locacao.setValor(4d);
        builder.locacao.setFilme(Arrays.asList(filmeBuilder().build()));
        builder.locacao.setDataLocacao(new Date());
        builder.locacao.setDataRetorno(DataUtils.obterDataComDiferencaDias(2));
        builder.locacao.setUsuario(UsuarioBuilder.umUsuario().build());

        return builder;
    }

    public LocacaoBuilder umaLocacao(){
        this.locacao.setDataLocacao(new Date());
        return this;
    }

    public LocacaoBuilder comDataRetorno(Date date){
        this.locacao.setDataRetorno(date);
        return this;
    }

    public Locacao build(){
        return this.locacao;
    }

    public LocacaoBuilder comUsuario(Usuario usuario) {
        this.locacao.setUsuario(usuario);
        return this;
    }
}
