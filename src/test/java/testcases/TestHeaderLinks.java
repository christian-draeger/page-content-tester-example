package testcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.jsoup.select.Elements;

import pagecontenttester.fetcher.Page;

public class TestHeaderLinks {

    public void check(Page fetchedPage) {
        Elements flightLink = fetchedPage.getDocument().select(".pageHeader-verticalListItemLink.wt-flight-vertical");
        assertThat(flightLink.attr("href"), is("http://flug.idealo.de"));

        Elements hotelLink = fetchedPage.getDocument().select(".pageHeader-verticalListItemLink.wt-hotel-vertical");
        assertThat(hotelLink.attr("href"), is("http://hotel.idealo.de"));

        Elements idealoLogo = fetchedPage.getDocument().select(".pageHeader-logoLink");
        assertThat(idealoLogo.attr("href"), is("/"));

        Elements cobiLogo = fetchedPage.getDocument().select(".cobi-linkImage");
        assertThat(cobiLogo.attr("alt"), is("Computer Bild Logo"));
    }

}
