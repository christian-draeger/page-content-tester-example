package testcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static testcases.util.ElementFinder.assertAndGetSingleElement;

import org.jsoup.select.Elements;

import pagecontenttester.fetcher.Page;

public class TestInfoText {

    public void check(Page fetchedPage, boolean isInfotextShown) {
        if (isInfotextShown)
        {
            assertAndGetSingleElement(fetchedPage, "#st-category-text");
            assertAndGetSingleElement(fetchedPage, ".info-text-title");
        } else {
            Elements infoText = fetchedPage.getDocument().select("#st-category-text");
            assertThat(infoText.size(), is(0));
        }

    }
}
