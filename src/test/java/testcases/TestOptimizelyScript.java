package testcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assume.assumeTrue;
import static testcases.matchers.JsonHasStringEntry.jsonHasStringEntry;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.select.Elements;

import pagecontenttester.fetcher.Page;
import testcases.util.StageScanner;

public class TestOptimizelyScript {

    private static final String OPTIMIZELY_PRODUCTION_SCRIPT_SRC = "cdn.optimizely.com/js/293218973.js";

    private static final String WEBTREKK_OPTIMIZELY_SCRIPT_SRC = "/lib/webtrekk_optimizely.js";
    private static final String WEBTREKK_OPTIMIZELY_INCLUDE = "wt_optimizely";
    private static final String WEBTREKK_FREQUENCY_ANALYSIS_INCLUDE = "wt_frequencyanalysis";

    public void with(Page fetchedPage) throws JSONException {

        assumeTrue(new StageScanner().getStage().equals("PRODUCTION"));

        String scriptSrc = OPTIMIZELY_PRODUCTION_SCRIPT_SRC;
        Elements scriptCandidates = fetchedPage.getDocument().select("script[src~=" + scriptSrc + "]");
        assertThat("optimizely script (" + scriptSrc + ") not included.", scriptCandidates.size(), is(1));

        Elements webtrekkScriptCandidates = fetchedPage.getDocument().select("script[src~=" + WEBTREKK_OPTIMIZELY_SCRIPT_SRC + "]");
        assertThat("webtrekk optimizely script (" + WEBTREKK_OPTIMIZELY_SCRIPT_SRC + ") not included.", webtrekkScriptCandidates.size(), is(1));

        Pattern pattern = Pattern.compile(".*var webtrekkConfig = (\\{.*?\\});.*", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(fetchedPage.getDocument().outerHtml());
        assertThat("webtrekk config not found.", matcher.matches(), is(true));

        JSONObject webtrekkConfigJson = new JSONObject(matcher.group(1));
        assertThat(webtrekkConfigJson, jsonHasStringEntry("executePluginFunction", allOf(
                containsString(WEBTREKK_OPTIMIZELY_INCLUDE),
                containsString(WEBTREKK_FREQUENCY_ANALYSIS_INCLUDE)
        )));
    }
}
