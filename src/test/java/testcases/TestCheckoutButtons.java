package testcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.jsoup.select.Elements;

import pagecontenttester.fetcher.Page;

public class TestCheckoutButtons {

    public void check(Page fetchedPage) {
        Elements ecommerceCheckoutBannerButton = fetchedPage.getDocument().select("td.title .checkout-button");
        Elements ecommerceCheckoutButton = fetchedPage.getDocument().select("td.cta .checkout-button");
        Elements leadoutLinkOnECommerceOffer = fetchedPage.getDocument().select(".special-offer .offer-title");

        // with if link on both E-commerce buttons match
        assertThat(ecommerceCheckoutBannerButton.attr("href"), is(ecommerceCheckoutButton.attr("href")));

        // with if link of checkout and offer only distinguish in type
        assertThat(ecommerceCheckoutButton.attr("href"), is(leadoutLinkOnECommerceOffer.attr("href").replace("type=offer", "type=checkout")));
    }
}
