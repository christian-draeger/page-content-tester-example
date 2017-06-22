package testcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.jsoup.select.Elements;

import pagecontenttester.fetcher.Page;


public class TestMicroData {

    public void check(final Page fetchedPage) {
        Elements productReview = fetchedPage.getDocument().select("#product-review");
        Elements productUserReviewSummary = fetchedPage.getDocument().select("#product-user-review-summary");
        assertThat(productReview.attr("itemtype"), is("http://www.data-vocabulary.org/Review"));
        assertThat(productReview.attr("itemprop"), is("review"));
        assertThat(productUserReviewSummary.attr("itemtype"), is("http://www.data-vocabulary.org/Review"));
        assertThat(productUserReviewSummary.attr("itemprop"), is("review"));
    }
}
