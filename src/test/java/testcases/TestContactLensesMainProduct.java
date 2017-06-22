package testcases;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.jsoup.select.Elements;

import pagecontenttester.fetcher.Page;

public class TestContactLensesMainProduct {

    public void check(Page fetchedPage) {
        checkVariantSelectionExists(fetchedPage);
        checkSortOrder(fetchedPage);
    }

    private void checkVariantSelectionExists(Page fetchedPage) {
        assertThat("Selectbox for Dioptrien is missing", fetchedPage.getDocument().select("#oopStageVariantFilter1").size(), is(1));
    }

    protected void checkSortOrder(Page fetchedPage) {
        Elements dioprienOptions = fetchedPage.getDocument().select("#oopStageVariantFilter1 option");
        List<String> dioptrienValues = dioprienOptions.stream()
                .map(option -> option.text())
                .collect(toList());

        assertThat(dioptrienValues.get(0), is("alle anzeigen"));
        for (int i = 1; i < dioptrienValues.size() - 1; i++) {
            double val1 = getDoubleValue(dioptrienValues.get(i));
            double val2 = getDoubleValue(dioptrienValues.get(i + 1));
            if (val2 < 0) {
                assertTrue(val1 + " > " + val2, val1 > val2);
            } else {
                assertTrue(val1 + " < " + val2, val1 < val2);
            }
        }

    }

    private double getDoubleValue(String s) {
        if ("+/-0.00".equals(s)) {
            return 0;
        }
        return Double.valueOf(s);
    }
}
