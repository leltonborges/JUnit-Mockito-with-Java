package br.ce.wcaquino.matchers;

import br.ce.wcaquino.utils.DataUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import java.util.Calendar;
import java.util.Date;
import static br.ce.wcaquino.utils.DataUtils.obterDataComDiferencaDias;

public class DiaMatcher extends TypeSafeMatcher<Date> {

    private Integer days;

    public DiaMatcher(Integer days) {
        this.days = days;
    }

    @Override
    protected boolean matchesSafely(Date data) {
        return DataUtils.isMesmaData(data, obterDataComDiferencaDias(days));
    }

    @Override
    public void describeTo(Description description) {

    }
}
