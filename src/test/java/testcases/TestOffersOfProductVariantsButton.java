package testcases;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;

import org.hamcrest.Matcher;

import pagecontenttester.fetcher.Page;

public class TestOffersOfProductVariantsButton {

    private final Page fetchedPage;

    public TestOffersOfProductVariantsButton(final Page fetchedPage) {
        this.fetchedPage = fetchedPage;
    }

    public void assertProductVariantsButtonHasOtherVariantsCount(int otherVariantsCount) {
        assertThat(fetchedPage.getDocument().select(".oopStage-variantWrapper-count").first().text(), is(Integer.toString(otherVariantsCount)));
    }

    public void assertProductVariantsButtonContainsText(Matcher<String> textMatcher) {
        assertThat(fetchedPage.getDocument().select(".oopStage-variantWrapper a").first().text(), is(textMatcher));
    }


}
