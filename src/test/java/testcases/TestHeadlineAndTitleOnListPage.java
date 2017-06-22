package testcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

import org.jsoup.select.Elements;

import pagecontenttester.fetcher.Page;

public class TestHeadlineAndTitleOnListPage {

    public void check(Page fetchedPage) {

        Elements title = fetchedPage.getDocument().select("title");
        Elements headline = fetchedPage.getDocument().select("h1");

        String fetchedTitle = title.text();
        String fetchedHeadline = headline.text();

        assertThat(fetchedTitle, containsString(fetchedHeadline));

    }

}
