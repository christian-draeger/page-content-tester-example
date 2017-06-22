package testcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import pagecontenttester.fetcher.Page;

public class TestIpcUid {

    public void check(Page fetchedPage) {

        // with a/b test cookie
        String cookieValue = fetchedPage.getCookieValue("ipcuid");
        // value has to have 18 characters
        assertThat(cookieValue.length(), is(18));
        // value has to contain small letters and numbers only
        assertThat(cookieValue.matches("^[a-z0-9_]+$"), is(true));

    }
}
