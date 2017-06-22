package testcases;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import pagecontenttester.fetcher.Page;

public class TestShopOpinionFormPage {

    private static final String EXPECTED_EMAIL_PARAM_VALUE = "ipc-qa@idealo.de";
    private static final String EXPECTED_BESTELLNUMMER_PARAM_VALUE = "123bestellnr";

    public void check(Page fetchedPage) {
        emailAddressIsPresentInInputField(fetchedPage);
        orderNumberIsPresentInInputField(fetchedPage);
    }

    private void emailAddressIsPresentInInputField(Page fetchedPage) {
        String formFieldValue = fetchedPage.getDocument().select("#ratingform > ul > li:nth-child(3) > input").val();

        assertThat(formFieldValue, is(equalTo(EXPECTED_EMAIL_PARAM_VALUE)));
    }

    private void orderNumberIsPresentInInputField(Page fetchedPage) {
        String formFieldValue = fetchedPage.getDocument().select("#ratingform > ul > li:nth-child(4) > input").val();

        assertThat(formFieldValue, is(equalTo(EXPECTED_BESTELLNUMMER_PARAM_VALUE)));
    }
}
