package testcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;
import java.util.List;

import org.jsoup.select.Elements;

import pagecontenttester.fetcher.Page;

public class TestFooterLinks {

    private final List<String> SHOPPING_URLS = Arrays.asList(
            "/preisvergleich/AlleHersteller.html",
            "/preisvergleich/AllePartner.html");
    private final List<String> SHOPPING_URLS_MOBILE = Arrays.asList(
            "www.idealo.de/preisvergleich/AlleHersteller.html",
            "www.idealo.de/preisvergleich/AllePartner.html");
    private final List<String> TRAVEL_URLS = Arrays.asList(
            "http://flug.idealo.de",
            "http://hotel.idealo.de",
            "https://mietwagen.idealo.de",
            "http://reise.idealo.de/bus/");
    private final List<String> COMPANY_URLS = Arrays.asList(
            "https://www.idealo.de/unternehmen/ueber-uns",
            "https://www.idealo.de/unternehmen/presse",
            "https://www.idealo.de/unternehmen/jobs",
            "/preisvergleich/Friends.html",
            "https://partner.idealo.com/de/partner-werden/");
    private final List<String> COMPANY_URLS_MOBILE = Arrays.asList(
            "https://www.idealo.de/unternehmen/ueber-uns",
            "https://www.idealo.de/unternehmen/presse",
            "https://www.idealo.de/unternehmen/jobs",
            "www.idealo.de/preisvergleich/Friends.html",
            "https://partner.idealo.com/de/partner-werden/");
    private final List<String> FOLLOW_US_URLS = Arrays.asList(
            "https://nachrichten.idealo.de/?source=ihomepage_footer",
            "https://www.idealo.de/magazin/",
            "https://www.facebook.com/idealoDE/",
            "https://twitter.com/idealo_de",
            "https://partner.idealo.com/de/magazin/");
    private final List<String> FOLLOW_US_URLS_MOBILE = Arrays.asList(
            "https://nachrichten.idealo.de/?source=ihomepage_footer",
            "https://www.idealo.de/magazin/",
            "https://www.facebook.com/idealoDE/",
            "https://mobile.twitter.com/idealo_de",
            "https://partner.idealo.com/de/magazin/");
    private final List<String> INTERNATIONAL_URLS = Arrays.asList(
            ".idealo.at/",
            ".idealo.co.uk/",
            ".idealo.es/",
            ".idealo.fr/",
            ".idealo.in/",
            ".idealo.it/",
            ".idealo.pl/");
    private final List<String> GTAC_LINKS = Arrays.asList(
            "/preisvergleich/Datenschutz.html",
            "/preisvergleich/AGB.html");

    public void check(final Page fetchedPage) {
        Elements shoppingUrls = fetchedPage.getDocument().select(".pageFooter-serviceBox:nth-of-type(1) a");

        for (int i = 0; i < shoppingUrls.size(); i++) {
            if (fetchedPage.isMobile()) {
                assertThat(shoppingUrls.get(i).attr("href"), endsWith(SHOPPING_URLS_MOBILE.get(i)));
            } else {
                assertThat(shoppingUrls.get(i).attr("href"), endsWith(SHOPPING_URLS.get(i)));
            }
        }

        Elements travelUrls = fetchedPage.getDocument().select(".pageFooter-serviceBox:nth-of-type(2) a");

        for (int i = 0; i < travelUrls.size(); i++) {
            assertThat(travelUrls.get(i).attr("href"), is(TRAVEL_URLS.get(i)));
        }

        Elements companyUrls = fetchedPage.getDocument().select(".pageFooter-serviceBox:nth-of-type(3) a");

        for (int i = 0; i < companyUrls.size(); i++) {
            if (fetchedPage.isMobile()) {
                assertThat(companyUrls.get(i).attr("href"), endsWith(COMPANY_URLS_MOBILE.get(i)));
            } else {
                assertThat(companyUrls.get(i).attr("href"), is(COMPANY_URLS.get(i)));
            }
        }

        Elements followUsUrls = fetchedPage.getDocument().select(".pageFooter-serviceBox:nth-of-type(4) a");

        for (int i = 0; i < followUsUrls.size(); i++) {
            if (fetchedPage.isMobile()) {
                assertThat(followUsUrls.get(i).attr("href"), is(FOLLOW_US_URLS_MOBILE.get(i)));
            } else {
                assertThat(followUsUrls.get(i).attr("href"), is(FOLLOW_US_URLS.get(i)));
            }
        }

        Elements gTacUrls = fetchedPage.getDocument().select(".pageFooter-gtacLink");

        for (int i = 0; i < gTacUrls.size(); i++) {
            assertThat(gTacUrls.get(i).attr("href"), endsWith(GTAC_LINKS.get(i)));
        }

        Elements internationalUrls = fetchedPage.getDocument().select(".pageFooter-internationalLink");

        for (int i = 0; i < internationalUrls.size(); i++) {
            if (!fetchedPage.isMobile()) {
                assertThat(internationalUrls.get(i).attr("href"),
                        either(endsWith("www" + INTERNATIONAL_URLS.get(i)))
                                .or(endsWith("www" + INTERNATIONAL_URLS.get(i).replace("/", ":" + fetchedPage.getConfig().getPort() + "/"))));
            } else {
                assertThat(internationalUrls.get(i).attr("href"),
                        either(endsWith("m" + INTERNATIONAL_URLS.get(i)))
                                .or(endsWith("m" + INTERNATIONAL_URLS.get(i).replace("/", ":" + fetchedPage.getConfig().getPort() + "/"))));
            }
        }
    }
}
