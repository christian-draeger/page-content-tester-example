package testcases.util;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import pagecontenttester.fetcher.Page;

public class ElementFinder {

    public static Element assertAndGetSingleElement(Page fetchedPage, String cssSelector) {
        Elements candidates = fetchedPage.getDocument().select(cssSelector);
        assertThat("No element found for selector \"" + cssSelector + "\" on " + fetchedPage.getUrl(),
                candidates.size(), Matchers.greaterThan(0));
        assertThat("More than one element found for selector \"" + cssSelector + "\" on " + fetchedPage.getUrl(),
                candidates.size(), Matchers.lessThan(2));
        return candidates.get(0);
    }

    public static Element assertAndGetFirstElement(Page fetchedPage, String cssSelector) {
        Elements candidates = fetchedPage.getDocument().select(cssSelector);
        assertThat("No element found for selector \"" + cssSelector + "\" on " + fetchedPage.getUrl(),
                candidates.size(), Matchers.greaterThan(0));
        return candidates.get(0);
    }
}
