package testcases;


import static org.junit.Assert.assertTrue;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import pagecontenttester.fetcher.Page;

public class TestThousandDelimiter {

    // Zahlem im Format - 1.000 1.000.000 usw.
    private static final String REGEX = "^(\\d{1,3}\\.(\\d{3}\\.)*\\d{3}(\\.\\d{1,3})?|\\d{1,3}(\\.\\d{3})?)$";

    public void check(Page fetchedPage) {
        // Category and Color filter on list page
        if (fetchedPage.getUrl().contains("/Liste")) {
            Elements categoryFilter = fetchedPage.getDocument().select("#filter-category li");
            for (Element category : categoryFilter) {
                assertTrue(getNumberOfResultsAsString(category.text()).matches(REGEX));
            }
            Elements colorFilter = fetchedPage.getDocument().select("#filter-color ul span");
            for (Element color : colorFilter) {
                assertTrue(getNumberOfResultsAsString(color.text()).matches(REGEX));
            }
        }
        // Category filter on search
        if (fetchedPage.getUrl().contains("/MainSearchProductCategory")) {

            Elements categoryFilter = fetchedPage.getDocument().select(".cat-list li span[class='quantity']");
            for (Element category : categoryFilter) {
                assertTrue(getNumberOfResultsAsString(category.text()).matches(REGEX));
            }
        }
        // Category filter on Pcat and Catalog
        if (fetchedPage.getUrl().contains("/ProductCategory/") || fetchedPage.getUrl().contains("/Katalog")) {
            Elements categoryFilter = fetchedPage
                    .getDocument()
                    .select(".search-filter li:not(.hidden):not(.more):not(.header):not(.bold) a span,.search-filter li:not(.hidden):not(.more):not(.header):not(.bold) label span");
            for (Element category : categoryFilter) {
                assertTrue(getNumberOfResultsAsString(category.text()).matches(REGEX));
            }
        }
        // Filter and selected Filter on PCatFilters
        if (fetchedPage.getUrl().contains("/ProductCategoryFilters")) {
            Elements resultFilterbox = fetchedPage.getDocument().select(".selected-filter-results");
            for (Element resultFilter : resultFilterbox) {
                String numberOfResultsString = resultFilter.text();
                numberOfResultsString = numberOfResultsString.substring(0, numberOfResultsString.indexOf(" "));
                assertTrue(numberOfResultsString.matches(REGEX));
            }
            Elements extendedFilters = fetchedPage.getDocument().select(".extended-filter a");
            for (Element extendedFilter : extendedFilters) {
                assertTrue(getNumberOfResultsAsString(extendedFilter.text()).matches(REGEX));
            }
        }
        // Prices on SubPCat
        if (fetchedPage.getUrl().contains("/SubProductCategory")) {
            Elements numberOfOffers = fetchedPage.getDocument().select(".cta > a > span:last-of-type");
            for (Element numberOfOffer : numberOfOffers) {
                String numberOfOffersString = numberOfOffer.text();
                numberOfOffersString = numberOfOffersString.replaceAll("[^0-9\\.]", "");
                assertTrue(numberOfOffersString.matches(REGEX));
            }
        }

    }

    private String getNumberOfResultsAsString(final String filterString) {
        String numberOfResultsAsString = filterString.substring(filterString.lastIndexOf("("));
        numberOfResultsAsString = numberOfResultsAsString.substring(0, numberOfResultsAsString.indexOf(")"));
        numberOfResultsAsString = numberOfResultsAsString.replace("(", "");
        numberOfResultsAsString = numberOfResultsAsString.replace("+", "");
        numberOfResultsAsString = numberOfResultsAsString.replace(")", "");
        numberOfResultsAsString = numberOfResultsAsString.replace(" ", "");
        numberOfResultsAsString = numberOfResultsAsString.replace("Angebote", "");
        numberOfResultsAsString = numberOfResultsAsString.trim();
        return numberOfResultsAsString;
    }
}
