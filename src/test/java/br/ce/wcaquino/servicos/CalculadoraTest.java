package br.ce.wcaquino.servicos;

import br.ce.wcaquino.exception.NotDivisionForZero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculadoraTest {

    Calculadora calc;

    @BeforeEach
    public void setup(){
        calc = new Calculadora();
    }

    @Test
    public void deveSomarDoisValores(){
        int a = 4;
        int b = 3;

        int result = calc.sum(a, b);;

        assertEquals(7, result);
    }

    @Test
    public void deveSubtrairDoisValores(){
        int a = 5;
        int b= 4;

        int result = calc.subtraction(a,b);
        assertEquals(1, result);
    }

    @Test
    public void deveDivisionDoisValores(){
        int a = 8;
        int b= 4;

        int result = calc.division(a,b);
        assertEquals(2, result);
    }

    @Test
    public void deveLancaExecaoDivindoPorZeroDoisValores(){
        int a = 8;
        int b= 0;

        assertThrows(NotDivisionForZero.class, () -> calc.division(a,b));
    }
}
