package testcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;

import org.jsoup.select.Elements;

import pagecontenttester.fetcher.Page;
import suites.de.XssTest;

public class TestXss {

    public void check(Page fetchedPage, XssTest.PageType pageType, XssTest.Results results) {

        Elements searchResults = fetchedPage.getDocument().select(".product-infos");
        Elements noResults = fetchedPage.getDocument().select("table table span");

        final String trimmedInput = fetchedPage.getUrl().substring(fetchedPage.getUrl().indexOf("=") + 1).trim();

        // Checks that the search field is clean of XSS vulnerabilities.
        assertSearchFieldClean(fetchedPage, trimmedInput, pageType);

        if (pageType.equals(XssTest.PageType.PRODUCTSEARCH)) {
            if (results.equals(XssTest.Results.WITH_RESULTS)) {
                // Checks that the page has results.
                assertThat(searchResults, is(not(empty())));
                assertThat("Results are present!", noResults.size(), is(0));
            } else {
                // Checks that the page has no results.
                assertThat(searchResults, is(empty()));
                // Checks that no results are present.
                assertThat("Results are present!", noResults.size(), greaterThan(0));
                // Checks that the no results info box is clean of XSS vulnerabilities.
                assertThat(noResults.text(), containsString(trimmedInput));
            }
        }

    }

    private void assertSearchFieldClean(Page fetchedPage, final String input, XssTest.PageType pageType) {
        Elements searchFormChilds = fetchedPage.getDocument().select(".input *");
        Elements searchForm = fetchedPage.getDocument().select(".input");
        Elements searchFormInput = fetchedPage.getDocument().select("#searchField");

        // Checks that the number of siblings from search field is one.
        if (pageType.equals(XssTest.PageType.PRODUCTSEARCH)) {
            assertThat(searchFormChilds.size(), is(2));
        } else {
            assertThat(searchFormChilds.size(), is(1));
        }

        // Checks that text of parent from search field is blank.
        assertThat(searchForm.text(), isEmptyString());

        // Checks that XSS attribute from search field is not present.
        assertThat(searchFormInput.attr(input), isEmptyString());

        // Checks that the input is equals to search field value.
        assertThat(searchFormInput.attr("value"), is(input));
    }

}
