package testcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

import org.jsoup.select.Elements;

import pagecontenttester.fetcher.Page;

public class TestHeadlineAndTitle {

    public void check(Page fetchedPage) {

        Elements title = fetchedPage.getDocument().select("title");
        Elements headline = fetchedPage.getDocument().select("h1");

        String fetchedTitle = title.toString();
        String fetchedHeadline = headline.toString();

        String expectedTitle = "IDEALO – die Nr. 1 im Preisvergleich";
        String expectedHeadlinePart = "Deutschlands großer Preisvergleich";

        assertThat(fetchedTitle, containsString(expectedTitle));
        assertThat(fetchedHeadline, containsString(expectedHeadlinePart));

    }

}
