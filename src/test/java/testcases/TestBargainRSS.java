package testcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assume.assumeTrue;

import org.jsoup.select.Elements;

import pagecontenttester.fetcher.Page;
import testcases.util.StageScanner;

public class TestBargainRSS {

    public void check(Page fetchedPage) {

        assumeTrue(new StageScanner().getStage().equals("PRODUCTION") || new StageScanner().getStage().equals("STAGING"));

        Elements title = fetchedPage.getDocument().select("title");
        assertThat(title.get(0).text(), is("Schnäppchen Feed"));
        Elements description = fetchedPage.getDocument().select("description");
        assertThat(description.get(0).text(), is("Ein Feed für alle Schnäppchen auf https://www.idealo.de"));
        Elements itemId = fetchedPage.getDocument().select("item>itemId");
        Elements guid = fetchedPage.getDocument().select("item>guid");
        assertThat("the itemId cannot be found in the guid-field", guid.get(0).text().contains(itemId.get(0).text()), is(true));
        Elements itemTitle = fetchedPage.getDocument().select("item>title");
        Elements itemDescription = fetchedPage.getDocument().select("item>description");
        assertThat("the item title cannot be found in the description", itemDescription.get(0).text().contains(itemTitle.get(0).text()), is(true));
    }
}
