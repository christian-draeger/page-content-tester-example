package testcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assume.assumeTrue;

import org.jsoup.select.Elements;

import pagecontenttester.fetcher.Page;
import testcases.util.StageScanner;

public class TestMainNavi {

    public void check(Page fetchedPage, int nodeSize) {

        assumeTrue(new StageScanner().getStage().equals("PRODUCTION") || new StageScanner().getStage().equals("STAGING"));

        Elements mainNaviNodes = fetchedPage.getDocument().select("#sidebar>.navi-main:first-of-type>li");
        assertThat("Size of main navi is incorrect", mainNaviNodes.size(), is(nodeSize));
        Elements allCategoriesLink = fetchedPage.getDocument().select(".navi-cat-all a");
        assertThat(allCategoriesLink.attr("href"), is("/preisvergleich/Sitemap.html"));
    }
}
