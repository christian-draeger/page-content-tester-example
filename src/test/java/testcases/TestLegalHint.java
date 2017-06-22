package testcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.jsoup.select.Elements;

import pagecontenttester.fetcher.Page;

public class TestLegalHint {

    private final String legalHintText = "Tous les résultats affichés proviennent de nos partenaires e-marchands et ne reflètent donc pas "
            + "l’intégralité des offres disponibles sur le marché.";
    private final String legalHintTextOffersOfProduct = "Avis aux utilisateurs : afin d’assurer un référencement de qualité pour nos internautes, "
            + "idealo vérifie au préalable le sérieux et la fiabilité de ses partenaires. Ainsi, tous les marchands ne sont pas listés sur idealo. "
            + "Les résultats ne reflètent donc pas obligatoirement l’intégralité des offres disponibles sur le marché. "
            + "Par défaut, les offres sont classées par prix ; l’offre la moins chère apparaît en première position.";

    public void check(Page fetchedPage) {
        if (fetchedPage.getUrl().contains("/prix/")) {
            Elements legalHint = fetchedPage.getDocument().select(".productOffers-listItemOfferHint");
            assertThat("legal hint text is wrong or changed", legalHint.text(), is(legalHintTextOffersOfProduct));
        } else {
            Elements legalHint = fetchedPage.getDocument().select(".offer-hint");
            assertThat("legal hint text is wrong or changed", legalHint.attr("title"), is(legalHintText));
        }
    }
}
