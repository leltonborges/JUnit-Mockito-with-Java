package br.ce.wcaquino.servicos;

import br.ce.wcaquino.exception.NotDivisionForZero;

public class Calculadora {
    public Integer sum(final Integer num1, final Integer num2) {
        return num1 + num2;
    }

    public Integer subtraction(final Integer num1, final Integer num2) {
        return num1 - num2;
    }

    public Integer division(Integer num1, Integer num2) {
        if(num2.equals(0))
            throw new NotDivisionForZero("Não pode ser dividio por (0) zero");
        return num1 / num2;
    }
}
