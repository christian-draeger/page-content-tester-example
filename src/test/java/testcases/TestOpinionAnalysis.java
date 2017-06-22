package testcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

import org.jsoup.select.Elements;

import pagecontenttester.fetcher.Page;


public class TestOpinionAnalysis {

    public void check(final Page fetchedPage) {

        // siblings of '#Meinungen' (user review)
        Elements authorElement = fetchedPage.getDocument().select("#Meinungen ~ .editorialProductText .textris strong:eq(0)");
        Elements opinionElement = fetchedPage.getDocument().select("#Meinungen ~ .editorialProductText .textris p:gt(0)");

        // The author has to be there...
        assertThat(authorElement.size(), is(1));
        // ...and it has to be text inside
        assertThat(authorElement.text().length(), greaterThan(0));

        // There has to be text elements
        assertThat(opinionElement.size(), greaterThan(0));
        //...with text inside
        assertThat(opinionElement.text().length(), greaterThan(0));
    }
}
