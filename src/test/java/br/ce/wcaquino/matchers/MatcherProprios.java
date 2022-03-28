package br.ce.wcaquino.matchers;

import java.util.Calendar;

public class MatcherProprios {

    public static DiaSemanaMatcher caiEm (Integer diaSemana){
        return new DiaSemanaMatcher(diaSemana);
    }

    public static DiaSemanaMatcher caiNumaSegunda(){
        return new DiaSemanaMatcher(Calendar.MONDAY);
    }
    
    public static DiaMatcher ehHoje(){
        return new DiaMatcher(0);
    }

    public static DiaMatcher ehHojeComDiferenciaDeDias(Integer days){
        return new DiaMatcher(days);
    }
}
