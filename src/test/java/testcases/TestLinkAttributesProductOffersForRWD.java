package testcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import pagecontenttester.fetcher.Page;

public class TestLinkAttributesProductOffersForRWD {

    public void check(Page fetchedPage) {
        Document document = fetchedPage.getDocument();

        checkNoFollow(document);
        checkAllImagesHasAnAltText(document);
    }

    private void checkNoFollow(final Document document) {
        // titles
        checkElementsForNoFollow(document, ".productOffers-listItemTitle");
        // prices
        checkElementsForNoFollow(document, "a.productOffers-listItemOfferPrice");
        // shop logos
        checkElementsForNoFollow(document, ".productOffers-listItemOfferLogoLink");
        // go to shop
        checkElementsForNoFollow(document, ".productOffers-listItemOfferCtaLeadout");

        // external shop ratings
        checkElementsForNoFollow(document, ".productOffers-listItemOfferRatingstext[href*=Relocate]");

        // internal shop rating - with that there is no "nofollow"
        checkElementsForWithoutNoFollow(document, ".productOffers-listItemOfferRatingstext:not([href*=Relocate])");
    }

    private void checkAllImagesHasAnAltText(Document document) {
        Elements imagesWithAltText = document.select("div.slide img[alt][alt!='']");
        Elements allImages = document.select("div.slide img");

        assertThat(allImages.size(), is(imagesWithAltText.size()));
    }

    private void checkElementsForNoFollow(final Document document, final String elementClass) {
        Elements all = document.select(elementClass);
        Elements withNoFollow = document.select(elementClass + "[rel~=nofollow]");
        assertThat(withNoFollow.size(), is(all.size()));
    }

    private void checkElementsForWithoutNoFollow(final Document document, final String elementClass) {
        Elements all = document.select(elementClass);
        Elements withNoFollow = document.select(elementClass + ":not([rel~=nofollow])");
        assertThat(withNoFollow.size(), is(all.size()));
    }
}
