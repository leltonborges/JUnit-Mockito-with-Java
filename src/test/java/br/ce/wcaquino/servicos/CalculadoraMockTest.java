package br.ce.wcaquino.servicos;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class CalculadoraMockTest {

    @Mock
    private Calculadora calcMock;

    @Captor
    private ArgumentCaptor<Integer> intCapt;

    @Spy
    private Calculadora calcSpy;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void devoMostrarDiferencaEntreMockSpy() {
        when(calcMock.sum(2, 4)).thenReturn(4);
        when(calcMock.sum(4,1)).thenCallRealMethod();
        System.out.println("Mock 1: " + calcMock.sum(2, 4)); // return 4 conforme espelho
        System.out.println("Mock 2: " + calcMock.sum(4, 4)); // não saberá o q fazer e return 0
        System.out.println("Mock 3: " + calcMock.sum(4, 1)); // execulta o metodo padrão da classe, não funciona com interfaces

        when(calcSpy.sum(2, 4)).thenReturn(4);
        System.out.println("Spy 1: " + calcSpy.sum(2, 4)); // return 4 conforem espelho
        System.out.println("Spy 2: " + calcSpy.sum(4, 4)); // execulta o metodo padrão da classe, não funciona com interfaces

        doReturn(5).when(calcSpy).sum(5,5);// não irá execultar o método real
        System.out.println("Spy im: " + calcSpy.sum(5, 5)); // return 5 conforem espelho, sem execultar o método real

        System.out.println("Mock imprime 1:");
        calcMock.imprimir();

        System.out.println("Spy imprime 1:");
        calcSpy.imprimir();

        //Spy sem retorno
        System.out.println("Spy imprime 2:");
        doNothing().when(calcSpy).imprimir();
        calcSpy.imprimir(); // não irá execultar o método real
    }

    @Test
    public void captureArgumentsMock() {

        when(calcMock.sum(intCapt.capture(), intCapt.capture())).thenReturn(4);

        Integer sum = calcMock.sum(5598, 564);
        assertEquals(Integer.valueOf(4), sum);

        System.out.println(intCapt.getAllValues());
    }
}
