package testcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.jsoup.select.Elements;

import pagecontenttester.fetcher.Page;

public class TestMainNaviTestLinks {

    public void check(Page fetchedPage, String testLink) {
        Elements allTestProducts = fetchedPage.getDocument().select(".link-request");
        assertThat(allTestProducts.attr("href"), is(testLink));
    }
}
