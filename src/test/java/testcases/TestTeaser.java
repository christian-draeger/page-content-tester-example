package testcases;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

import pagecontenttester.fetcher.Page;

public class TestTeaser {

    public void checkUniversalTeaser(final Page fetchedPage) {
        assertThat(fetchedPage.getElement("#teaserBanner").outerHtml(), containsString("universal"));
    }

    public void check(final Page fetchedPage) {
        assertThat(fetchedPage.isElementPresent("#teaserBanner"), is(true));
    }
}
