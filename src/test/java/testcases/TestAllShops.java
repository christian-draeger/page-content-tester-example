package testcases;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashSet;
import java.util.Set;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import pagecontenttester.fetcher.Page;


public class TestAllShops {

    public void check(Page fetchedPage) {
        shopCertificatesArePresent(fetchedPage);
        shopRatingStarIsPresent(fetchedPage);
        linksForFirstShopAreConsistent(fetchedPage);
    }

    private Set<String> getImageLinks(Page fetchedPage) {
        Elements allImages = fetchedPage.getDocument().getElementsByTag("img");
        Set<String> imageLinks = new HashSet<>();
        for (Element image : allImages) {
            imageLinks.add(image.absUrl("src"));
        }
        return imageLinks;
    }

    private void linksForFirstShopAreConsistent(Page fetchedPage) {
        String shopNameLink = fetchedPage.getDocument().select("#offers-list > tbody > tr:nth-child(2) > td:nth-child(1) > p > a").attr("href");
        String ratingLink = fetchedPage.getDocument().select("#offers-list > tbody > tr:nth-child(2) > td:nth-child(2) > div > a").attr("href").replace("#Bewertung", "");
        String logoLink = fetchedPage.getDocument().select("#offers-list > tbody > tr:nth-child(2) > td:nth-child(3) > a").attr("href");

        assertThat(shopNameLink, allOf(equalTo(logoLink), equalTo(ratingLink)));
    }

    private void shopRatingStarIsPresent(Page fetchedPage) {
        final String RATING_STAR = "pics/common/gifspacer.gif";
        Set<String> imageLinks = getImageLinks(fetchedPage);

        assertThat(imageLinks, hasItems(endsWith(RATING_STAR)));
    }

    private void shopCertificatesArePresent(Page fetchedPage) {
        final String EHI_LOGO = "Certificate/1/0/1001/s1_logo_klein.gif";
        final String TRUSTED_SHOPS = "folder/Certificate/1/0/1010/s1_logo_klein.gif";
        final String TUEV_SUED = "folder/Certificate/1/0/1006/s1_logo_klein.gif";
        Set<String> imageLinks = getImageLinks(fetchedPage);

        assertThat(imageLinks, hasItems(
                endsWith(EHI_LOGO),
                endsWith(TRUSTED_SHOPS),
                endsWith(TUEV_SUED)));
    }
}
