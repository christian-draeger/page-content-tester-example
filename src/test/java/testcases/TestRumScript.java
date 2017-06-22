package testcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;
import static org.junit.Assume.assumeTrue;

import java.util.Map;

import org.json.JSONException;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import pagecontenttester.fetcher.Page;
import testcases.util.StageScanner;
import testcases.util.TestPageContentUtils;

public class TestRumScript {

    public void check(Page fetchedPage, String pageTemplate) throws JSONException {

        assumeTrue(new StageScanner().getStage().equals("PRODUCTION") || new StageScanner().getStage().equals("STAGING"));

        Elements rumScripts = fetchedPage.getDocument().select("script[src~=ipc-rum\\.js]");
        assertThat("ipc-rum.js script not included.", rumScripts.size(), is(1));
        Element rumScript = rumScripts.get(0);
        assertThat("Missing \"data-rum\" attribute.", rumScript.hasAttr("data-rum"), is(true));
        Map<String, String> dataAsMap = TestPageContentUtils.getJsonAsMap(rumScript.attr("data-rum"));
        assertThat(dataAsMap, allOf(
                hasEntry("site", "www.idealo.de"),
                hasEntry("page_template", pageTemplate),
                hasEntry("target", "//l.idealo.com/api/stats"),
                hasEntry("distribution", "10"),
                hasEntry("cycle", "7")
        ));
    }
}
