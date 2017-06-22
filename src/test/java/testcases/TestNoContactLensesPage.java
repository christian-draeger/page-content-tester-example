package testcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import pagecontenttester.fetcher.Page;

public class TestNoContactLensesPage {

    public void check(Page fetchedPage) {
        checkVariantSelectionDoesNotExist(fetchedPage);
    }

    private void checkVariantSelectionDoesNotExist(Page fetchedPage) {
        assertThat("Selectbox for Dioptrien is availabe but should not", fetchedPage.getDocument().select("#oopStageVariantFilter1").size(), is(0));
    }
}
