package testcases;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import pagecontenttester.fetcher.Page;

public class TestShopPage {

    public void check(Page fetchedPage) {
        linksForFirstShopAreConsistent(fetchedPage);
    }

    private void linksForFirstShopAreConsistent(Page fetchedPage) {
        String shopNameLink = fetchedPage.getDocument().select("#sidebar > div > div:nth-child(1) > span").text();

        assertThat(shopNameLink, is(equalTo("Top Kategorien")));
    }
}
