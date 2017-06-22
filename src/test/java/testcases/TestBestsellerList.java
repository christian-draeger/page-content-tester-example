package testcases;

import static testcases.util.ElementFinder.assertAndGetFirstElement;
import static testcases.util.ElementFinder.assertAndGetSingleElement;

import pagecontenttester.fetcher.Page;

public class TestBestsellerList {
    public void check(final Page fetchedMobilePage) {
        // Check that "Bestseller" Headline exists
        assertAndGetSingleElement(fetchedMobilePage, ".offerList-header h2");
        // Check product image exists
        assertAndGetFirstElement(fetchedMobilePage, ".offerList-item-imageWrapper");
        // Check price value exists
        assertAndGetFirstElement(fetchedMobilePage, ".priceRange");
        // Check product title exists
        assertAndGetFirstElement(fetchedMobilePage, ".offerList-item-description-title");
    }
}
