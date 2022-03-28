package br.ce.wcaquino.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import br.ce.wcaquino.servicos.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CalcValorLotacaoTest.class,
        LocacaoServiceTest.class
})
public class SuiteExecucao {
    //Remova se puder
}
