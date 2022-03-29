package br.ce.wcaquino.builders;

import br.ce.wcaquino.entidades.Filme;

public class FilmeBuilder {
    private Filme filme;

    private FilmeBuilder() {
    }

    public static FilmeBuilder filmeBuilder(){
        FilmeBuilder builder = new FilmeBuilder();
        builder.filme = new Filme("Filme 1", 3, 3.0);
        return builder;
    }

    public FilmeBuilder semEstoque(){
        filme.setEstoque(0);
        return this;
    }

    public FilmeBuilder comValor(Double valor){
        this.filme.setPrecoLocacao(valor);
        return this;
    }

    public Filme build(){
        return filme;
    }
}
