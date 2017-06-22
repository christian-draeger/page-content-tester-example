package testcases;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pagecontenttester.fetcher.Page;

public class TestIdealoLogo {

    private Optional<Element> logoLink=Optional.empty();
    private Optional<Element> logo=Optional.empty();
    private String expectedLogoSrcEnding="";

    private static final String EXPECTED_ALT_TEXT = "idealo - Deutschlands großer Preisvergleich";
    private static final String EXPECTED_LOGO_LINK = "/";

    private static final IdealoLogoLayoutInfo[] knownLayouts = {
            new IdealoLogoLayoutInfo(".pageHeader-logoLink",".pageHeader-logoLink img","/rwd/img/logo-idealo.svg"),
            new IdealoLogoLayoutInfo("[data-wt-linkid=header.home]","#logo-idealo-wrapper img","pics/logos/logo_header.png"),
            new IdealoLogoLayoutInfo("#logo-idealo-wrapper > a","#logo-idealo-wrapper > a > img","pics/logos/logo_header.png")
    };

    public static TestIdealoLogo isPresent() {
        return new TestIdealoLogo();
    }

    private TestIdealoLogo() {}

    public void check(Page fetchedPage) {

        matchKnownLayouts(fetchedPage);

        assertThat("no logoLink found on "+fetchedPage.getUrl(),logoLink,not(Optional.empty()));
        assertThat("no logo found on "+fetchedPage.getUrl(),logo,not(Optional.empty()));

        String logoLinkUrl = logoLink.get().attr("href");
        assertThat("Unexpected URL in idealo logo link: " + logoLinkUrl + " on " + fetchedPage.getUrl(),
                logoLinkUrl, is(EXPECTED_LOGO_LINK ));

        assertThat("Unexpected URL in idealo logo image: " + logoLinkUrl + " on " + fetchedPage.getUrl(),
                logo.get().attr("src"), endsWith(expectedLogoSrcEnding));
        assertThat("Unexpected alt attribute in logo image: "+ logoLinkUrl + " on " + fetchedPage.getUrl(),
                logo.get().attr("alt"), is(EXPECTED_ALT_TEXT));
    }

    protected void matchKnownLayouts(final Page fetchedPage) {
        for(int i=0;i<knownLayouts.length;i++) {
            Elements logoLinkCandidates = fetchedPage.getDocument().select(knownLayouts[i].getLogoLinkSelector());
            Elements logoCandidates = fetchedPage.getDocument().select(knownLayouts[i].getLogoImageSelector());

            assertThat("multiple logoLinks found on " + fetchedPage.getUrl(),logoLinkCandidates.size(),lessThanOrEqualTo(1));
            assertThat("multiple logos found on " + fetchedPage.getUrl(),logoCandidates.size(),lessThanOrEqualTo(1));

            if (logoLinkCandidates.size()==1 && logoCandidates.size()==1) {
                logoLink = Optional.of(logoLinkCandidates.first());
                logo = Optional.of(logoCandidates.first());
                expectedLogoSrcEnding = knownLayouts[i].logoSrcEnding;
                break;
            }
        }
    }


}

@Getter
@AllArgsConstructor
class IdealoLogoLayoutInfo {
    String logoLinkSelector;
    String logoImageSelector;
    String logoSrcEnding;
}
