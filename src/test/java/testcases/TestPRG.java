package testcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

import org.jsoup.select.Elements;

import pagecontenttester.fetcher.Page;

public class TestPRG {

    public void check(Page fetchedPage, boolean usePRG) {
        Elements linksForPRG = fetchedPage.getDocument().select("label>input");
        Elements linksWithoutPRG = fetchedPage.getDocument().select(".filter-tagItem a");
        if (usePRG) {
            assertThat(linksForPRG.size(), greaterThan(0));
            for (int i = 0; i < linksForPRG.size(); i++) {
                assertThat(linksForPRG.attr("name"), is("valueId"));
            }
        } else {
            assertThat(linksWithoutPRG.size(), greaterThan(0));
            for (int i = 0; i < linksWithoutPRG.size(); i++) {
                assertThat(linksWithoutPRG.attr("href"), containsString("ProductCategory"));
            }
        }

    }

}
