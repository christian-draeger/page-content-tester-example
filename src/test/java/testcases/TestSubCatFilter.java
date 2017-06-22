package testcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.jsoup.select.Elements;

import pagecontenttester.fetcher.Page;

/**
 * Created by siham.el-messari on 11.05.17.
 */

public class TestSubCatFilter {

    public void check(Page fetchedPage) {
        Elements mobileSubCatFilterCategories = fetchedPage.getDocument().select(".accordion-listItem .accordion-serviceBoxListItem:first-of-type");
        Elements categoryBoxes = fetchedPage.getDocument().select(".accordion-listItem");
        assertThat("mobile subcatFilter Categories not visible", mobileSubCatFilterCategories.size(), is(categoryBoxes.size()));
    }

}

