package testcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

import org.jsoup.select.Elements;

import pagecontenttester.fetcher.Page;

public class TestProductAnalysis {

    public void check(Page fetchedPage) {
        Elements textElement = fetchedPage.getDocument().select("#Expertenmeinung>.textris>div");
        assertThat(textElement.text().length(), greaterThan(0));
    }
}
