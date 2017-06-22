package testcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.jsoup.select.Elements;

import pagecontenttester.fetcher.Page;

public class TestErrorPage {

    public void check(Page fetchedPage) {
        Elements errorPage = fetchedPage.getDocument().select(".errorpage");
        assertThat(errorPage.size(), is(1));
    }
}
