package testcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import pagecontenttester.fetcher.Page;

public class TestPriceWatcherAjaxResponse {

    public void check(Page fetchedPage) {
        Document document = fetchedPage.getDocument();
        Elements filterElements = document.select("#awp-data-layer");
        assertThat(filterElements, hasSize(1));
    }
}
