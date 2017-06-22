package testcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import pagecontenttester.fetcher.Page;

public class TestAltText {

    public void check(Page fetchedPage) throws Exception {
        Document document = fetchedPage.getDocument();

        Elements offerTitle = document.select(".offerList-item-description-title");
        Elements offerImageAltTitle = document.select(".offerList-item-image");

        assertThat(offerTitle.size(), is(greaterThan(0)));
        assertThat(offerImageAltTitle.size(), is(greaterThan(0)));

        for (int i=0; i < offerTitle.size(); i++) {
            assertThat(offerTitle.get(i).text(), is(offerImageAltTitle.get(i).attr("alt")));
        }
    }
}
