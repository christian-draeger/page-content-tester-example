package testcases.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import pagecontenttester.fetcher.Page;

public class TestHttpStatusCode {

    public void check(Page fetchedPage, final int statusCode) {
        assertThat("Page " + fetchedPage + " returned wrong status code " + statusCode, fetchedPage.getStatusCode(), is(statusCode));
    }

}
