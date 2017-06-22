package testcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.jsoup.select.Elements;

import pagecontenttester.fetcher.Page;

public class TestOffersOfProductFilterCheckboxes {

    private Elements checkbox;

    public void assertFreeReturnFilterIsChecked(final Page fetchedPage) {
        whenSelectingFreeReturnCheckbox(fetchedPage);
        thenCheckboxIsChecked();
    }

    public void assertAvailableFilterIsChecked(final Page fetchedPage) {
        whenSelectingFreeReturnCheckbox(fetchedPage);
        thenCheckboxIsChecked();
    }

    public void assertFreeReturnFilterIsNotChecked(final Page fetchedDesktopPageParamTestingZero) {
        whenSelectingFreeReturnCheckbox(fetchedDesktopPageParamTestingZero);
        thenCheckboxIsNotChecked();
    }

    public void assertAvailableFilterIsNotChecked(final Page fetchedDesktopPageParamTestingZero) {
        whenSelectingAvailableCheckbox(fetchedDesktopPageParamTestingZero);
        thenCheckboxIsNotChecked();
    }

    private void whenSelectingFreeReturnCheckbox(final Page fetchedPage) {
        checkbox = fetchedPage.getDocument().select("#product-offers-filter-freereturn");
    }

    private void whenSelectingAvailableCheckbox(final Page fetchedPage) {
        checkbox = fetchedPage.getDocument().select("#product-offers-filter-available");
    }

    private void thenCheckboxIsChecked() {
        assertThat(checkbox.hasAttr("checked"), is(true));
    }

    private void thenCheckboxIsNotChecked() {
        assertThat(checkbox.hasAttr("checked"), is(false));
    }
}
