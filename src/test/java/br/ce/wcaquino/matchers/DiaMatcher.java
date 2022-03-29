package br.ce.wcaquino.matchers;

import br.ce.wcaquino.utils.DataUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
        Date dataEsperada = obterDataComDiferencaDias(days);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        description.appendText(dateFormat.format(dataEsperada));
    }
}
