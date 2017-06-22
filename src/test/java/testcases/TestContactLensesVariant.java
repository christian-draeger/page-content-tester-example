package testcases;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;

import pagecontenttester.fetcher.Page;

public class TestContactLensesVariant extends TestContactLensesMainProduct {

    public void check(Page fetchedPage) {
        super.check(fetchedPage);
        checkVariantIsSelected(fetchedPage);
    }

    private void checkVariantIsSelected(Page fetchedPage) {
        assertThat(fetchedPage.getDocument().select("#oopStageVariantFilter1 option[selected]").text(), is("-0.25"));
    }

}
