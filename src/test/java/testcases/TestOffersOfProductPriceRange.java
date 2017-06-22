package testcases;


import static org.hamcrest.core.StringContains.containsString;
import static org.hamcrest.junit.MatcherAssert.assertThat;
import static org.hamcrest.text.MatchesPattern.matchesPattern;

import pagecontenttester.fetcher.Page;

public class TestOffersOfProductPriceRange {

    public void assertPriceRangeIsVisible(final Page fetchedPage) {
        thenPriceRangeIsVisible(fetchedPage);
    }

    private void thenPriceRangeIsVisible(final Page fetchedPage) {
        assertThat(fetchedPage.getDocument().select(".oopStage-priceRangeOffers").first().text(), containsString("Angebote"));
        assertThat(fetchedPage.getDocument().select(".oopStage-priceRangePrice").first().text(), matchesPattern("\\d*,\\d\\d\\S€ – \\d[0-9.]*,\\d\\d\\S€"));
    }

}
