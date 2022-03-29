package br.ce.wcaquino.builders;

import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import java.util.Date;

public class LocacaoBuilder {
    private Locacao locacao;

    private  LocacaoBuilder(){
    }

    public static LocacaoBuilder create(){
        LocacaoBuilder builder = new LocacaoBuilder();
        builder.locacao = new Locacao();

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
