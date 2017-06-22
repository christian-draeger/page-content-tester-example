package testcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;

import org.jsoup.select.Elements;

import pagecontenttester.fetcher.Page;
import suites.de.XssTest;

public class TestRwdXss {

    public void check(Page fetchedPage, XssTest.PageType pageType, XssTest.Results results) {

        Elements searchResults = fetchedPage.getDocument().select(".offerList-item");
        Elements noResults = fetchedPage.getDocument().select(".no-result");
        Elements selectedFilters = fetchedPage.getDocument().select(".filterTag-item");

        final String trimmedInput = fetchedPage.getUrl().substring(fetchedPage.getUrl().lastIndexOf("q") + 2).trim();

        if (pageType.equals(XssTest.PageType.MAINSEARCH)) {
            // Checks that the meta tag is clean of XSS vulnerabilities.
            assertMetaTagClean(fetchedPage, trimmedInput);
        }

        // Checks that the search field is clean of XSS vulnerabilities.
        assertSearchFieldClean(fetchedPage, trimmedInput);

        if (results.equals(XssTest.Results.WITH_RESULTS)) {

            // Checks that the page has results.
            assertThat(searchResults.size(), greaterThan(0));
            assertThat("Results are present!", noResults.size(), is(0));

            if (pageType.equals(XssTest.PageType.PCAT)) {
                assertThat(selectedFilters.text(), containsString(trimmedInput));
            }

        } else {
            // Checks that the page has no results.
            assertThat(searchResults.size(), is(0));
            // Checks that no results are present.
            assertThat("Results are present!", noResults.size(), greaterThan(0));

            // Checks that the no results info box is clean of XSS vulnerabilities.
            assertMainSearchProductCategoryNoResultsInfoBoxClean(fetchedPage, trimmedInput);
        }

    }

    /**
     * Checks that the meta description contains the search query.
     */
    private void assertMetaTagClean(Page fetchedPage, final String input) {
        Elements metaTag = fetchedPage.getDocument().select("meta[name='description']");
        assertThat("Description meta tag does not contain search query!", metaTag.attr("content"), containsString(input));
    }

    private void assertSearchFieldClean(Page fetchedPage, final String input) {
        Elements searchFormChilds = fetchedPage.getDocument().select(".search-form *");
        Elements searchForm = fetchedPage.getDocument().select(".search-form");
        Elements searchFormInput = fetchedPage.getDocument().select(".search-formInput");

        // Checks that the number of siblings from search field is one.
        assertThat(searchFormChilds.size(), is(3));

        // Checks that text of parent from search field is blank.
        assertThat(searchForm.text(), isEmptyString());

        // Checks that XSS attribute from search field is not present.
        assertThat(searchFormInput.attr(input), isEmptyString());

        // Checks that the input is equals to search field value.
        assertThat(searchFormInput.attr("value"), is(input));
    }

    /**
     * Checks that the no results info box is present and that the title of this box contains the search query.
     */
    private void assertMainSearchProductCategoryNoResultsInfoBoxClean(Page fetchedPage, final String input) {

        Elements noResultsHeader = fetchedPage.getDocument().select(".no-result .no-result-infoHeader");
        Elements noResultsImage = fetchedPage.getDocument().select(".no-result .no-result-image");

        // Checks that title of no results box is equals to input of search form.
        assertThat(noResultsHeader.text(), containsString(input));
        // Checks that the no result image is here
        assertThat(noResultsImage.attr("src"), endsWith("/rwd/img/no-result.png"));
    }

}
